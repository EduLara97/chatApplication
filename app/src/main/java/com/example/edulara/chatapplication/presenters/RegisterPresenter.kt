package com.example.edulara.chatapplication.presenters

import android.graphics.Bitmap
import android.net.Uri
import com.example.edulara.chatapplication.activities.RegisterActivity
import com.example.edulara.chatapplication.models.Contact
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by Edwin on 2/11/2018.
 */
class RegisterPresenter(val view : RegisterActivity) {

    private val mAuth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().reference
    private var filePath: Uri? = null
    private var storage : FirebaseStorage? = FirebaseStorage.getInstance()
    private var storageReference : StorageReference? = storage!!.reference

    /*private val storage = FirebaseStorage.getInstance().reference
    private val uidUser = FirebaseAuth.getInstance().currentUser!!.uid*/

    fun createUserDBReference(uid: String, values: Contact) {
        db.child("users/$uid").setValue(values)
    }

    fun register(email:String, password : String, name:String, genre:String, image: Bitmap?){
        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !genre.isEmpty()) {
            val imagePath:String?
            if(image==null){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(view) { task ->
                            run {
                                if (task.isSuccessful) {
                                    createUserDBReference(task.result!!.user.uid,
                                            Contact(name, email, genre, ""))
                                    view.correctLogin()
                                } else view.incorrectLogin("Fallo de autenticación")
                            }
                        }
            }else{
                val imageRef = storageReference!!.child("images/" + UUID.randomUUID().toString())
                val baos = ByteArrayOutputStream()
                image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                imageRef.putBytes(data).addOnSuccessListener {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(view) { task ->
                                run {
                                    if (task.isSuccessful) {
                                        createUserDBReference(task.result!!.user.uid,
                                                Contact(name, email, genre, imageRef.path))
                                        view.correctLogin()
                                    } else view.incorrectLogin("Fallo de autenticación")
                                }
                            }
                }
            }



        } else view.incorrectLogin("Ingresar credenciales")
    }

    interface RegisterDelegate{
        fun correctLogin()
        fun incorrectLogin(error : String)
    }
}