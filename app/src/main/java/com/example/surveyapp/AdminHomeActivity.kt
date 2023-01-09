package com.example.surveyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Survey

class AdminHomeActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        val myDatabase = DataBaseHelper(this)
        val surveys = myDatabase.getSurvey()


        val surveyList = findViewById<ListView>(R.id.myListView)

        //Create the adapter
        val customAdapter = CustomAdapter(applicationContext, surveys)
        surveyList!!.adapter = customAdapter
    }

    fun createBtn(view: View) {
        val createIntent = Intent(this, CreateSurveyActivity::class.java)
        startActivity(createIntent)
    }

    fun back(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}