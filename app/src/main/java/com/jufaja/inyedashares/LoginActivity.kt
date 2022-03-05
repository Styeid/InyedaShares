package com.jufaja.inyedashares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            goHomeActivity()
        }

        btnlogin.setOnClickListener {
            btnlogin.isEnabled = false
            val email = etemail.text.toString()
            val password = etpassword.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter your\nEmail and Password", Toast.LENGTH_SHORT)
                    .show()
            return@setOnClickListener
        }
        // Firebase authentication check
            //val auth = FirebaseAuth.getInstance() ==>> Moved to rule to creat user session
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                btnlogin.isEnabled = true
                if (task.isSuccessful) {
                    Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show()
                    goHomeActivity()
                } else {
                    Log.i(TAG, "Info. Login has failed")
                    Toast.makeText(this, "Login failure", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goHomeActivity() {
        Log.i(TAG, "Info. goHomeactivity")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}