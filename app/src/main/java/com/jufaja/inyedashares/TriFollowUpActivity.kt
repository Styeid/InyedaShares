package com.jufaja.inyedashares

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.activity_sec_follow_up.*
import kotlinx.android.synthetic.main.activity_tri_follow_up.*

private const val TAG = "TriFollowUpActivity"
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
}