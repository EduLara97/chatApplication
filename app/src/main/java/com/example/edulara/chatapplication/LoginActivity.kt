package com.example.edulara.chatapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        toolbar.title = "Ingresar"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickLogIn(view : View){
        if(!edtEmail.text!!.toString().isEmpty() && !edtPassword.text.toString().isEmpty()){
            mAuth.signInWithEmailAndPassword(edtEmail.text!!.toString(),edtPassword.text!!.toString())
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful) correctLogin()
                        else incorrectLogin("Fallo de autenticación")
                    }
        }else incorrectLogin("Ingresar credenciales")
    }

    private fun correctLogin(){
        startActivity(Intent(this, ListChatsActivity::class.java))
    }

    private fun incorrectLogin(error:String){
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
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

}
