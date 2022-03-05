package com.jufaja.inyedashares

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.activity_data_list.*
import kotlinx.android.synthetic.main.item_dataposta.view.*


private const val TAG = "DataListActivity"

open class DataListActivity : AppCompatActivity() {

    var dataPostLimit: Long = 20
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var postz: MutableList<DataPost>
    private lateinit var adapterHome: HomeDataAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_list)

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
        val datapostReference = firestoreDb
            .collection("datapost")
            .limit(dataPostLimit)
            .orderBy("abdate", Query.Direction.DESCENDING)
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_history_data, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.icdatahistory) {
            Log.i(TAG, "Info. Go to DataHistory/RefreshData")
            val intent = Intent(this, DataListActivity::class.java)
            finish()
            Toast.makeText(this, "Data History\n\tRefresh", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.icfirstfollowup) {
            Log.i(TAG, "Info. Go to FirstFollowUp/RefreshData")
            val intent = Intent(this, FirstFollowUpActivity::class.java)
            finish()
            Toast.makeText(this, "First follow up\n\tRefresh", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.icsecfollowup) {
            Log.i(TAG, "Info. Go to SecFollowUp/RefreshData")
            val intent = Intent(this, SecFollowUpActivity::class.java)
            finish()
            Toast.makeText(this, "Sec. follow up\n\tRefresh", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.ictrifollowup) {
            Log.i(TAG, "Info. Go to TriFollowUp/RefreshData")
            val intent = Intent(this, TriFollowUpActivity::class.java)
            finish()
            Toast.makeText(this, "Tri. follow up\n\tRefresh", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
            return super.onOptionsItemSelected(item)
        }

}