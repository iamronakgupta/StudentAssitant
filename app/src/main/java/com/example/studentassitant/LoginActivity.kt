package com.example.studentassitant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var googleSignInButton: SignInButton
    private val RC_SIGN_IN=369
    private lateinit var auth:FirebaseAuth
    var currentUser:FirebaseUser?=null
    var accountName:String?=null
    var accountPhoto:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleSignInButton = findViewById(R.id.sign_in_Button)
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD)
        googleSignInButton.setOnClickListener{
            signIn()
        }
        auth= FirebaseAuth.getInstance()
        currentUser = auth.currentUser
        if (currentUser!=null) {
            startMain()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("350068175240-tklct8ru7n8g482nd9pb66h7fp03r865.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account!=null) {
            startMain()
        }

        googleSignInClient=GoogleSignIn.getClient(this,gso)


    }

    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun startMain() {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==RC_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInTask(task)
        }
    }

    private fun handleSignInTask(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account=completedTask.getResult(ApiException::class.java)
            Log.d("Auth", "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
            startMain()
        }catch (e:ApiException){
            Log.w("TAG","signInFailed code:"+e.statusCode)
            Toast.makeText(this,"Sign In Failed!!! Retry",Toast.LENGTH_LONG).show()
        }
        }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Auth", "signInWithCredential:success")
                    currentUser=auth.currentUser
                    accountName= currentUser?.displayName
                    accountPhoto=currentUser?.photoUrl
                    startMain()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Auth", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this,"Sign In Failed!!! Retry",Toast.LENGTH_LONG).show()
                }
            }

    }

}


