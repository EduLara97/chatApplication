package com.example.edulara.chatapplication.presenters

import com.example.edulara.chatapplication.activities.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Edwin on 2/11/2018.
 */
class RegisterPresenter(val view : RegisterActivity) {

    private val mAuth = FirebaseAuth.getInstance()

    fun register(email:String, password : String, name:String, genre:String, image:String?){
        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !genre.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(view) { task ->
                        run {
                            if (task.isSuccessful) view.correctLogin()
                            else view.incorrectLogin("Fallo de autenticación")
                        }
                    }
        } else view.incorrectLogin("Ingresar credenciales")
    }

    interface RegisterDelegate{
        fun correctLogin()
        fun incorrectLogin(error : String)
    }
}