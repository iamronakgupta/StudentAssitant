package com.example.studentassitant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class FirstActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
        }, 10000)

        auth= FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser!=null) {
            startMain()
        }else{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }



    }

    private fun startMain() {

            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

    }
}