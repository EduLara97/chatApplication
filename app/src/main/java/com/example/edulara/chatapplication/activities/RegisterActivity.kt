package com.example.edulara.chatapplication.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.example.edulara.chatapplication.R
import com.example.edulara.chatapplication.presenters.RegisterPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterPresenter.RegisterDelegate {

    private val mAuth = FirebaseAuth.getInstance()
    private val mPresenter = RegisterPresenter(this)
    private val CAMERA_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar.title = "Crear Nuevo Usuario"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvChangePhoto.setOnClickListener {
            val bo : Boolean = checkCameraHardware(this)
            if(bo){
                val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) makeRequest()
                else openCamera()
            }
        }
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

    fun onCheckBoxClicked(view : View){
        if(view is CheckBox){
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkBoxMale -> if (checked) if(checkBoxFemale.isChecked) checkBoxFemale.toggle()
                R.id.checkBoxFemale -> if (checked) if(checkBoxMale.isChecked)checkBoxMale.toggle()
            }
        }
    }

    fun onClickNewUserButton(view:View){
        val g:String? = when {
            checkBoxFemale.isChecked -> "F"
            checkBoxMale.isChecked -> "H"
            else -> ""
        }
        mPresenter.register(edtEmail.text.toString(),
                edtNewPassword.text.toString(),
                edtName.text.toString(),g.toString(),null)

    }

    private fun checkCameraHardware(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            ivPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
            ivPhoto.setImageBitmap(imageBitmap)
        }
    }

    override fun correctLogin(){
        startActivity(Intent(this, ListChatsActivity::class.java))
        finish()
    }

    override fun incorrectLogin(error:String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

}
