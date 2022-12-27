package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.surveyapp.Model.DataBaseHelper

class AdminHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        var surveyList: ListView
        val myDatabase = DataBaseHelper(this)

        var surveyTitles = myDatabase.getSurveyTitle()
        var startDateList = myDatabase.getStartDate()
        var endDateList = myDatabase.getEndDate()

        val edit = findViewById<Button>(R.id.buttonEdit)
        edit.isEnabled = false

        surveyList = findViewById<ListView>(R.id.myListView)

        surveyList.setOnItemClickListener { parent, view, position, id ->
            edit.isEnabled = true
        }

        //Create the adapter
        val customAdapter = CustomAdapter(applicationContext, surveyTitles, startDateList, endDateList)
        surveyList!!.adapter = customAdapter
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