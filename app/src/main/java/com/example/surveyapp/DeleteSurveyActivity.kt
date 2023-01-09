package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Survey

class DeleteSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_survey)

        val warning = findViewById<TextView>(R.id.textViewWarning)
        val survey = intent.getSerializableExtra("Survey") as Survey
        val title = survey.title

        warning.text = "Are you sure you want to delete this survey for $title?"
    }

    fun delete(view: View) {
        val myDatabase = DataBaseHelper(this)
        val survey = intent.getSerializableExtra("Survey") as Survey
        val id = survey.SurveyID
        val title = survey.title
        val start = survey.startDate
        val end = survey.endDate
        myDatabase.deleteSurvey(Survey(id, title, start, end))
        myDatabase.deleteQuestions(Survey(id, title, start, end))

        Toast.makeText(this, "Survey for $title has been deleted.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AdminHomeActivity::class.java)
        startActivity(intent)
    }

    fun back(view: View) {
        finish()
    }
}