package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.surveyapp.Model.Answers
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Survey

class StudentQuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_questions)

        val back = findViewById<Button>(R.id.btnPrevQ)
        back.isEnabled = false
        back.alpha = .5f

        val survey = intent.getSerializableExtra("Survey") as Survey
        val id = survey.SurveyID
        val title = survey.title
        val start = survey.startDate
        val end = survey.endDate

        val newSurvey = Survey(id, title, start, end)

        val loadQuestions = myDatabase.getQuestions(newSurvey)

        val question = findViewById<TextView>(R.id.textViewCurrentQuestion)
        question.text = loadQuestions[0]

        val group = findViewById<RadioGroup>(R.id.radioGroupAnswers2)
        group.setOnCheckedChangeListener { group, checkedId ->
            var answer: String = ""
            when (checkedId) {
                R.id.radioButtonSA2 -> answer = "5"

                R.id.radioButtonA2 -> answer = "4"

                R.id.radioButtonN2 -> answer = "3"

                R.id.radioButtonD2 -> answer = "2"

                R.id.radioButtonSD2 -> answer = "1"
            }

            answers[counter] = answer
        }
    }

    fun homeButton(view: View) {
        finish()
    }

    var counter = 0

    val myDatabase = DataBaseHelper(this)

    val answers = arrayListOf("", "", "", "", "", "", "", "", "", "")

    fun backButton(view: View) {
        val survey = intent.getSerializableExtra("Survey") as Survey
        val id = survey.SurveyID
        val title = survey.title
        val start = survey.startDate
        val end = survey.endDate

        val newSurvey = Survey(id, title, start, end)

        val loadQuestions = myDatabase.getQuestions(newSurvey)
        val group = findViewById<RadioGroup>(R.id.radioGroupAnswers2)
        val next = findViewById<Button>(R.id.btnNextQ)
        val submit = findViewById<Button>(R.id.btnSubmit)
        val question = findViewById<TextView>(R.id.textViewCurrentQuestion)

        counter -= 1
        question.text = loadQuestions[counter]

        group.clearCheck()

        val back = findViewById<Button>(R.id.btnPrevQ)

        if(counter == 0) {
            back.isEnabled = false
            back.alpha = .5f
        }

        if (counter < 9) {
            submit.isVisible = false
            next.isVisible = true
        }
    }

    fun nextButton(view: View) {
        val group = findViewById<RadioGroup>(R.id.radioGroupAnswers2)

        if (group.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
        }

        else {
            counter += 1
            val question = findViewById<TextView>(R.id.textViewCurrentQuestion)

            val survey = intent.getSerializableExtra("Survey") as Survey
            val id = survey.SurveyID
            val title = survey.title
            val start = survey.startDate
            val end = survey.endDate

            val newSurvey = Survey(id, title, start, end)

            val loadQuestions = myDatabase.getQuestions(newSurvey)
            question.text = loadQuestions[counter]
            group.setOnCheckedChangeListener { group, checkedId ->
                var answer: String = ""
                when (checkedId) {
                    R.id.radioButtonSA2 -> answer = "5"

                    R.id.radioButtonA2 -> answer = "4"

                    R.id.radioButtonN2 -> answer = "3"

                    R.id.radioButtonD2 -> answer = "2"

                    R.id.radioButtonSD2 -> answer = "1"
                }

                answers[counter] = answer
            }

            group.clearCheck()

            val back = findViewById<Button>(R.id.btnPrevQ)

            if(counter > 0) {
                back.isEnabled = true
                back.alpha = 1.0f
            }

            val next = findViewById<Button>(R.id.btnNextQ)
            val submit = findViewById<Button>(R.id.btnSubmit)

            when(counter) {
                9 -> {
                    next.isVisible = false
                    submit.isVisible = true
                }
            }
        }
    }

    //val group = findViewById<RadioGroup>(R.id.radioGroupAnswers2)

    fun submit(view: View) {
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra("Answers", answers)
        //intent.putExtra("Group", group)
        startActivity(intent)
    }
}