package com.example.edulara.chatapplication.presenters

import com.example.edulara.chatapplication.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Edwin on 2/11/2018.
 */
class LoginPresenter(val view : LoginActivity) {

    private val mAuth = FirebaseAuth.getInstance()

    fun login(email : String, password : String){
        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(view){task ->
                        if(task.isSuccessful) view.correctLogin()
                        else view.incorrectLogin("Fallo de autenticación")
                    }
        }else view.incorrectLogin("Ingresar credenciales")

    }

    interface LoginDelegate{
        fun correctLogin()
        fun incorrectLogin(error: String)
    }
}