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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.inyedashares.models.DataPost
import com.jufaja.inyedashares.models.User
import kotlinx.android.synthetic.main.activity_data_list.*
import kotlinx.android.synthetic.main.item_dataposta.view.*

private const val TAG = "DataListActivity"
private  const val EXTRA_USERNAME = "EXTRA_USERNAME"
open class DataListActivity : HomeActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_history_data, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.icdatahistory) {
            Log.i(TAG, "Info. Go to FirstFollowUp/RefreshData")
            val intent = Intent(this, DataListActivity::class.java)
            //intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            finish()
            Toast.makeText(this, "Data History\n\tRefresh", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.icfirstfollowup) {
            Log.i(TAG, "Info. Go to FirstFollowUp/RefreshData")
            val intent = Intent(this, FirstFollowUpActivity::class.java)
            //intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            finish()
            Toast.makeText(this, "First follow up\n\tRefresh", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.icsecfollowup) {
            Log.i(TAG, "Info. Go to SecFollowUp/RefreshData")
            val intent = Intent(this, SecFollowUpActivity::class.java)
            //intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            finish()
            Toast.makeText(this, "Sec. follow up\n\tRefresh", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        if (item.itemId == R.id.ictrifollowup) {
            Log.i(TAG, "Info. Go to TriFollowUp/RefreshData")
            val intent = Intent(this, TriFollowUpActivity::class.java)
            //intent.putExtra(EXTRA_USERNAME, signedInUser?.username)
            finish()
            Toast.makeText(this, "Tri. follow up\n\tRefresh", Toast.LENGTH_SHORT).show()
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



