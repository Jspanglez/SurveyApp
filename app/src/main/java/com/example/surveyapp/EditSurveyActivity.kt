package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class EditSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_survey)

        val survey = intent.getStringExtra("Selected Item").toString()
        //val code = intent.getStringExtra("Titles").toString()
        val title = findViewById<TextView>(R.id.textViewEditTitle)
        title.text = survey

        /*when(survey) {
            "CTEC3911" -> title.text = "CTEC3911"
            "CTEC3905" -> title.text = "CTEC3905"
            "CTEC3906" -> title.text = "CTEC3906"
            "IMAT3423" -> title.text = "IMAT3423"
            "CTEC3451" -> title.text = "CTEC3451"
        }*/
    }

    fun nextBtn(view: View) {
        val startDate = findViewById<EditText>(R.id.editTextEditStartDate).text.toString()
        val endDate = findViewById<EditText>(R.id.editTextEditEndDate).text.toString()
        val intent = Intent(this, EditQuestionsActivity::class.java)
        intent.putExtra("Start", startDate)
        intent.putExtra("End", endDate)

        when {
            //Date validation

            else -> {
                startActivity(intent)
            }
        }
    }

    fun backBtn(view: View) {
        val intentBack = Intent(this, AdminHomeActivity::class.java)
        startActivity(intentBack)
    }
}