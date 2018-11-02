package com.example.edulara.chatapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.edulara.chatapplication.presenters.LoginPresenter
import com.example.edulara.chatapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginPresenter.LoginDelegate {

    private val mPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        toolbar.title = "Ingresar"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickLogIn(view : View){
        mPresenter.login(edtEmail.text!!.toString(), edtPassword.text!!.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun incorrectLogin(error : String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    override fun correctLogin() {

        startActivity(Intent(this, ListChatsActivity::class.java))
    }
}
