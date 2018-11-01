package com.example.edulara.chatapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar.title = "Crear Nuevo Usuario"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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


}
