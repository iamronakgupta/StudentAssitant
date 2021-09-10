package com.example.studentassitant

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Profile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var currentUser: FirebaseUser?=null
    var accountName:String?=null
    var accountPhoto: Uri?=null
    lateinit var localAccountName:TextView
    lateinit var localAccountPhoto:ImageView


    private lateinit var fab: ExtendedFloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        localAccountName=findViewById(R.id.profile_name)
        localAccountPhoto=findViewById(R.id.profile_Photo)
        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener{
            floatingButton();
        }
        auth= FirebaseAuth.getInstance()
        currentUser = auth.currentUser

        accountName=currentUser?.displayName
        accountPhoto=currentUser?.photoUrl
        setPersonalDetails()
    }
    private fun floatingButton(){

    }
    private fun setPersonalDetails() {
        localAccountName.text=accountName
        Glide.with(this).load(accountPhoto).into(localAccountPhoto)

    }
}