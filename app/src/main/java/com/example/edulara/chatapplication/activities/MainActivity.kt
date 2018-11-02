package com.example.edulara.chatapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.edulara.chatapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkCurrentUser()

        btnNewAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    fun checkCurrentUser(){
        if(mAuth.currentUser!=null){
            startActivity(Intent(this, ListChatsActivity::class.java))
        }
    }
}
