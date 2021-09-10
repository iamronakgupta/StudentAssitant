package com.example.studentassitant

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var topAppBarLayout: AppBarLayout
    lateinit var appBar:MaterialToolbar

    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser?=null
    private var accountName:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topAppBarLayout=findViewById(R.id.topBarLayout)
        appBar=findViewById(R.id.topAppBar)

        auth= FirebaseAuth.getInstance()
        currentUser = auth.currentUser
        if (currentUser==null) {
            startLogin()
        }
        accountName=currentUser?.displayName



        appBar.setNavigationOnClickListener {
            startProfile();
        }
        setPersonalDetails();






    }

    private fun setPersonalDetails() {
        appBar.title=accountName


    }

    private fun startLogin() {
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startProfile() {
        val intent=Intent(this,Profile::class.java)
        startActivity(intent)
    }
}