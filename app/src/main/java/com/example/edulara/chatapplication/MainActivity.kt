package com.example.edulara.chatapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNewAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}
