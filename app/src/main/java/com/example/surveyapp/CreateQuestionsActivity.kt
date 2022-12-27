package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Questions
import com.example.surveyapp.Model.Survey

class CreateQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_questions)
    }

    fun goBack(view: View) {
        val intentBack = Intent(this, CreateSurveyActivity::class.java)
        startActivity(intentBack)
    }

    fun createBtn(view: View) {
        val surveyTitle = intent.getStringExtra("Survey Name").toString()
        val startDate = intent.getStringExtra("Start").toString()
        val endDate = intent.getStringExtra("End").toString()
        val question1 = findViewById<EditText>(R.id.editTextUpdateQ1).text.toString()
        val question2 = findViewById<EditText>(R.id.editTextUpdateQ2).text.toString()
        val question3 = findViewById<EditText>(R.id.editTextUpdateQ3).text.toString()
        val question4 = findViewById<EditText>(R.id.editTextUpdateQ4).text.toString()
        val question5 = findViewById<EditText>(R.id.editTextUpdateQ5).text.toString()
        val question6 = findViewById<EditText>(R.id.editTextUpdateQ6).text.toString()
        val question7 = findViewById<EditText>(R.id.editTextUpdateQ7).text.toString()
        val question8 = findViewById<EditText>(R.id.editTextUpdateQ8).text.toString()
        val question9 = findViewById<EditText>(R.id.editTextUpdateQ9).text.toString()
        val question10 = findViewById<EditText>(R.id.editTextUpdateQ10).text.toString()

        when {

            question1.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question2.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question3.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question4.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question5.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question6.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question7.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question8.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question9.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            question10.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()

            else -> {
                val newSurvey = Survey(1, surveyTitle, startDate, endDate)
                val newQuestion1 = Questions(1, question1, newSurvey.SurveyID)
                val newQuestion2 = Questions(1, question2, newSurvey.SurveyID)
                val newQuestion3 = Questions(1, question3, newSurvey.SurveyID)
                val newQuestion4 = Questions(1, question4, newSurvey.SurveyID)
                val newQuestion5 = Questions(1, question5, newSurvey.SurveyID)
                val newQuestion6 = Questions(1, question6, newSurvey.SurveyID)
                val newQuestion7 = Questions(1, question7, newSurvey.SurveyID)
                val newQuestion8 = Questions(1, question8, newSurvey.SurveyID)
                val newQuestion9 = Questions(1, question9, newSurvey.SurveyID)
                val newQuestion10 = Questions(1, question10, newSurvey.SurveyID)
                //val questions = arrayOf(newQuestion1, newQuestion2, newQuestion3, newQuestion4, newQuestion5, newQuestion6, newQuestion7, newQuestion8, newQuestion9, newQuestion10)
                val myDatabase = DataBaseHelper(this)
                val addNewSurvey = myDatabase.addSurvey(newSurvey)
                myDatabase.addQuestions(newQuestion1)
                myDatabase.addQuestions(newQuestion2)
                myDatabase.addQuestions(newQuestion3)
                myDatabase.addQuestions(newQuestion4)
                myDatabase.addQuestions(newQuestion5)
                myDatabase.addQuestions(newQuestion6)
                myDatabase.addQuestions(newQuestion7)
                myDatabase.addQuestions(newQuestion8)
                myDatabase.addQuestions(newQuestion9)
                myDatabase.addQuestions(newQuestion10)

                when(addNewSurvey) {
                    1 -> Toast.makeText(this, "New survey for $surveyTitle has been created.", Toast.LENGTH_SHORT).show()

                    -1 -> Toast.makeText(this, "Error adding survey.", Toast.LENGTH_SHORT).show()
                }

                /*when(addNewQuestion1) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion2) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion3) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion4) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion5) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion6) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion7) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion8) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion9) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }

                when(addNewQuestion10) {
                    1 -> Toast.makeText(this, "", Toast.LENGTH_SHORT)//.show()
                }*/

                val intent = Intent(this, AdminHomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}