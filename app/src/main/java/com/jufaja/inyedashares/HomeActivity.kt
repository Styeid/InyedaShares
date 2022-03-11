package com.jufaja.inyedashares

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jufaja.inyedashares.models.DataPost
import com.jufaja.inyedashares.models.User
import kotlinx.android.synthetic.main.activity_data_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val TAG = "HomeActivity"
private  const val EXTRA_USERNAME = "EXTRA_USERNAME"
open class HomeActivity : AppCompatActivity() {

    var dataPostLimit: Long = 20
    var fieldOrderBy: String = "abdate"
    var signedInUser: User? = null
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var postz: MutableList<DataPost>
    private lateinit var adapterHome: HomeDataAdapter
    private val dataPostCollectionRef = Firebase.firestore.collection("datapost")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_list)
        //setHome()
        btnrefresha.setOnClickListener {
            Log.i(TAG,"Info. Go to RefreshData A")
            val intent = Intent(this,ARefreshDataActivity::class.java)
            Toast.makeText(this, "Refresh Data Fund A", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        ///////////////////////////////////////////////

        ///////////////////////////////////////////////
        //-----
        // 1 Create the layout file which represent one post = item_datapost(a,b,c,d)xml
        // 2 Create data source
        postz = mutableListOf()
        // 3 Create Adapter
        adapterHome = HomeDataAdapter(this, postz)
        // 4 Bind the adapter and layout manager to the recyclerview
        rvdataposta.adapter = adapterHome
        rvdataposta.layoutManager = LinearLayoutManager(this)
        //-----
        // retrieve data (query) from Firestore
        firestoreDb = FirebaseFirestore.getInstance()

        firestoreDb.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
            .get()
            .addOnSuccessListener { userSnapshot ->
                signedInUser = userSnapshot.toObject(User::class.java)
                Log.i(TAG, "Signed in user: $signedInUser")
            }
            .addOnFailureListener { exeption ->
                Log.i(TAG, "Failure fetching signed in user", exeption)
            }

        var datapostReference = firestoreDb
            .collection("datapost")
            .limit(dataPostLimit)
            .orderBy(fieldOrderBy, Query.Direction.DESCENDING)

        //-!-//__code for filtering by user__//
        //val username = intent.getStringExtra(EXTRA_USERNAME)
        //if (username != null) {
        //    supportActionBar?.title = username
        //    datapostReference = datapostReference.whereEqualTo("aauser.username", username)
        //}
        datapostReference.addSnapshotListener { snapshot, exeption ->
            if (exeption != null || snapshot == null) {
                Log.e(TAG, "Error. Exeption when querying datapost ")
                return@addSnapshotListener
            }
            val dataPostList = snapshot.toObjects(DataPost::class.java)
            postz.clear()
            postz.addAll(dataPostList)
            adapterHome.notifyDataSetChanged()
            for (dataPost in dataPostList) {
                Log.i(TAG, "dataPosts $dataPost")
            }
        }
        fabadddatapost.setOnClickListener {
            val intent = Intent(this,CreateActivity::class.java)
            startActivity(intent)
        }
    }
//////////////////////////////
    private fun setHome() = CoroutineScope(IO).launch {
        val lastdataQuery = dataPostCollectionRef
            .whereEqualTo("aauser", "Bram")
            .get()
            .await()
        if (lastdataQuery.documents.isNotEmpty()) {
            for (tils1 in lastdataQuery) {
                val dataBlok1 = firestoreDb.collection("datapost").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvfundnamea)
                val blok_Color1 = findViewById<TextView>(R.id.tvamountaxyz)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvvaluea)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("afpartyvaluea")
                                blok_Color1.text = document.getString("agpartyfundb")
                                blok_ImageU1.text = document.getString("ajpartyvalueb")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@HomeActivity, "Query = Empty", Toast.LENGTH_LONG).show()
            }
        }
    }
////////////////////////
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.icgodatalist) {
            Log.i(TAG, "Info. Go to FirstFollowUp/RefreshData")
            val intent = Intent(this, FirstFollowUpActivity::class.java)
            //intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            Toast.makeText(this, "FirstFollowUp\n\tRefresh", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.iclogout) {
            Log.i(TAG, "Info. User wants to logout")
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Going back to login screen", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}