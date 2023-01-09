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
        finish()
    }

    fun createBtn(view: View) {
        val surveyTitle = intent.getStringExtra("Survey Name").toString()
        val startDate = intent.getStringExtra("Start").toString()
        val endDate = intent.getStringExtra("End").toString()
        val question1 = findViewById<EditText>(R.id.editTextQ1).text.toString()
        val question2 = findViewById<EditText>(R.id.editTextQ2).text.toString()
        val question3 = findViewById<EditText>(R.id.editTextQ3).text.toString()
        val question4 = findViewById<EditText>(R.id.editTextQ4).text.toString()
        val question5 = findViewById<EditText>(R.id.editTextQ5).text.toString()
        val question6 = findViewById<EditText>(R.id.editTextQ6).text.toString()
        val question7 = findViewById<EditText>(R.id.editTextQ7).text.toString()
        val question8 = findViewById<EditText>(R.id.editTextQ8).text.toString()
        val question9 = findViewById<EditText>(R.id.editTextQ9).text.toString()
        val question10 = findViewById<EditText>(R.id.editTextQ10).text.toString()

        val questions = arrayOf(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10)

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

                val myDatabase = DataBaseHelper(this)
                val addNewSurvey = myDatabase.addSurvey(newSurvey)
                val id = myDatabase.getSurveyID()

                for(x in questions) {
                    val newQuestion = Questions(1, x, id)
                    myDatabase.addQuestions(newQuestion)
                }

                when(addNewSurvey) {
                    1 -> Toast.makeText(this, "New survey for $surveyTitle has been created.", Toast.LENGTH_SHORT).show()

                    -1 -> Toast.makeText(this, "Error adding survey.", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, AdminHomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}