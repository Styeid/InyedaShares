package com.jufaja.inyedashares

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.inyedashares.models.DataPost
import com.jufaja.inyedashares.models.User
import kotlinx.android.synthetic.main.activity_sec_follow_up.*
import kotlinx.android.synthetic.main.activity_tri_follow_up.*

private const val TAG = "TriFollowUpActivity"
private  const val EXTRA_USERNAME = "EXTRA_USERNAME"
class TriFollowUpActivity : DataListActivity() {

    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var postz: MutableList<DataPost>
    private lateinit var adapterHome: TriDataAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tri_follow_up)

        postz = mutableListOf()

        adapterHome = TriDataAdapter(this, postz)

        rvdatapostd.adapter = adapterHome
        rvdatapostd.layoutManager = LinearLayoutManager(this)

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

        val datapostReference = firestoreDb
            .collection("datapost")
            .limit(dataPostLimit)
            .orderBy(fieldOrderBy, Query.Direction.DESCENDING)

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
            //for (dataPost in dataPostList) {
            //    Log.i(TAG, "dataPosts $dataPost")
            //}
        }
    }
}