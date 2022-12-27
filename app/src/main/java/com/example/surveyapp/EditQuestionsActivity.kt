package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EditQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_questions)
    }

    fun btnBack(view: View) {
        val intent = Intent(this, EditSurveyActivity::class.java)
        startActivity(intent)
    }

    fun btnUpdate(view: View) {
        /**
         * if(myDatabase.updateSurvey(Survey(SurveyID, title, startDate, endDate)))
         *      Toast.makeText("Updated survey successfully")
         *      startActivity(intent)
         * else
         *      Toast.makeText("Error")
         *
         * if(myDatabase.updateQuestion(Question(QuestionID, questionText, SurveyID)))
         */
    }
}