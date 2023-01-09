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

class EditQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_questions)

        val survey = intent.getSerializableExtra("survey") as Survey
        val id = survey.SurveyID
        val title = survey.title
        val start = survey.startDate
        val end = survey.endDate

        val myDatabase = DataBaseHelper(this)
        val questions = myDatabase.getQuestions(Survey(id, title, start, end))

        val editQ1 = findViewById<EditText>(R.id.editTextUpdateQ1)
        val editQ2 = findViewById<EditText>(R.id.editTextUpdateQ2)
        val editQ3 = findViewById<EditText>(R.id.editTextUpdateQ3)
        val editQ4 = findViewById<EditText>(R.id.editTextUpdateQ4)
        val editQ5 = findViewById<EditText>(R.id.editTextUpdateQ5)
        val editQ6 = findViewById<EditText>(R.id.editTextUpdateQ6)
        val editQ7 = findViewById<EditText>(R.id.editTextUpdateQ7)
        val editQ8 = findViewById<EditText>(R.id.editTextUpdateQ8)
        val editQ9 = findViewById<EditText>(R.id.editTextUpdateQ9)
        val editQ10 = findViewById<EditText>(R.id.editTextUpdateQ10)

        editQ1.setText(questions[0])
        editQ2.setText(questions[1])
        editQ3.setText(questions[2])
        editQ4.setText(questions[3])
        editQ5.setText(questions[4])
        editQ6.setText(questions[5])
        editQ7.setText(questions[6])
        editQ8.setText(questions[7])
        editQ9.setText(questions[8])
        editQ10.setText(questions[9])

    }

    fun btnBack(view: View) {
        finish()
    }

    fun btnUpdate(view: View) {

        val survey = intent.getSerializableExtra("survey") as Survey
        val id = survey.SurveyID
        val title = survey.title
        val start = intent.getStringExtra("Start").toString()
        val end = intent.getStringExtra("End").toString()

        val editQ1 = findViewById<EditText>(R.id.editTextUpdateQ1).text.toString()
        val editQ2 = findViewById<EditText>(R.id.editTextUpdateQ2).text.toString()
        val editQ3 = findViewById<EditText>(R.id.editTextUpdateQ3).text.toString()
        val editQ4 = findViewById<EditText>(R.id.editTextUpdateQ4).text.toString()
        val editQ5 = findViewById<EditText>(R.id.editTextUpdateQ5).text.toString()
        val editQ6 = findViewById<EditText>(R.id.editTextUpdateQ6).text.toString()
        val editQ7 = findViewById<EditText>(R.id.editTextUpdateQ7).text.toString()
        val editQ8 = findViewById<EditText>(R.id.editTextUpdateQ8).text.toString()
        val editQ9 = findViewById<EditText>(R.id.editTextUpdateQ9).text.toString()
        val editQ10 = findViewById<EditText>(R.id.editTextUpdateQ10).text.toString()

        when {
            editQ1.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ2.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ3.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ4.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ5.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ6.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ7.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ8.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ9.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()
            editQ10.isNullOrBlank() -> Toast.makeText(this, "Please enter 10 questions.", Toast.LENGTH_SHORT).show()

            else -> {
                val myDatabase = DataBaseHelper(this)
                val questionIDs = myDatabase.getQuestionID(Survey(id, title, start, end))

                myDatabase.updateSurvey(Survey(id, title, start, end))
                myDatabase.updateQuestion(Questions(questionIDs[0], editQ1, id))
                myDatabase.updateQuestion(Questions(questionIDs[1], editQ2, id))
                myDatabase.updateQuestion(Questions(questionIDs[2], editQ3, id))
                myDatabase.updateQuestion(Questions(questionIDs[3], editQ4, id))
                myDatabase.updateQuestion(Questions(questionIDs[4], editQ5, id))
                myDatabase.updateQuestion(Questions(questionIDs[5], editQ6, id))
                myDatabase.updateQuestion(Questions(questionIDs[6], editQ7, id))
                myDatabase.updateQuestion(Questions(questionIDs[7], editQ8, id))
                myDatabase.updateQuestion(Questions(questionIDs[8], editQ9, id))
                myDatabase.updateQuestion(Questions(questionIDs[9], editQ10, id))

                //val update1 = Questions()

                /*for(x in questions) {
                    val updatedQuestion = Questions(1, x, id)
                    myDatabase.updateQuestion(updatedQuestion)
                }*/

                Toast.makeText(this, "Survey for $title has been updated successfully.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdminHomeActivity::class.java)
                startActivity(intent)
            }
        }


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