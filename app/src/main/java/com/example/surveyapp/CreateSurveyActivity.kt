package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CreateSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_survey)
    }

    fun nextBtn(view: View) {
        //Make validation here
        //if something is not selected: make a toast saying "Please complete all fields."


        //Push Survey Title, Start Date and End date to the database here
        /*val intentNext = Intent(this, CreateQuestionsActivity::class.java)
        startActivity(intentNext)*/
    }

    fun backBtn(view: View) {
        val intentBack = Intent(this, AdminHomeActivity::class.java)
        startActivity(intentBack)
    }
}