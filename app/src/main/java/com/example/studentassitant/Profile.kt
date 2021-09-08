package com.example.studentassitant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class Profile : AppCompatActivity() {
    private lateinit var fab: ExtendedFloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener{
            floatingButton();
        }
    }
    private fun floatingButton(){

    }
}