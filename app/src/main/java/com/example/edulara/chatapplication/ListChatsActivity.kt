package com.example.edulara.chatapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.edulara.chatapplication.R.id.menu_add
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list_chats.*

class ListChatsActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_chats)

        toolbar.title = "Chats"
        toolbar.inflateMenu(R.menu.menu_chats)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_account_circle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_chats, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_add->{

            }
            R.id.menu_close->{
                finish()
                mAuth.signOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
