package com.example.surveyapp

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Survey
import java.util.*

class StudentHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)

        val myDatabase = DataBaseHelper(this)
        //val surveys = myDatabase.getStudentSurvey()
        val surveys = myDatabase.getSurvey()

        val studentSurveys = arrayListOf<Survey>()
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        for(x in surveys) {
            if(x.startDate <= today && x.endDate > today)
                studentSurveys.add(x)
        }

        val surveyList = findViewById<ListView>(R.id.studentListview)

        //Create the adapter
        val customAdapter = StudentCustomAdapter(applicationContext, studentSurveys)
        surveyList!!.adapter = customAdapter
    }

    fun goBackButton(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}