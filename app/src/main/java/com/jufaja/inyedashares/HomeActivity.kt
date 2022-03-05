package com.jufaja.inyedashares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "HomeActivity"
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.icgodatalist) {
            Log.i(TAG, "Info. Go to Datalist 1.0")
            val intent = Intent(this, DataListActivity::class.java)
            Toast.makeText(this, "Data Screen", Toast.LENGTH_SHORT).show()
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