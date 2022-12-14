package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
    }

    fun createBtn(view: View) {
        val createIntent = Intent(this, CreateSurveyActivity::class.java)
        startActivity(createIntent)
    }

    fun editBtn(view: View) {
        val editIntent = Intent(this, EditSurveyActivity::class.java)
        startActivity(editIntent)
    }


}