package com.example.surveyapp

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.surveyapp.Model.Survey

class EditSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_survey)

        val survey = intent.getSerializableExtra("Survey") as Survey
        val module = survey.title
        val start = survey.startDate
        val end = survey.endDate
        val title = findViewById<TextView>(R.id.textViewMessage)
        title.text = "  Currently editing survey for:    $module"

        val startDate = findViewById<EditText>(R.id.editTextEditStartDate)
        val endDate = findViewById<EditText>(R.id.editTextEditEndDate)

        startDate.setText(start)
        endDate.setText(end)

    }

    fun nextBtn(view: View) {
        val startDate = findViewById<EditText>(R.id.editTextEditStartDate).text.toString()
        val endDate = findViewById<EditText>(R.id.editTextEditEndDate).text.toString()

        val survey = intent.getSerializableExtra("Survey") as Survey

        val intent = Intent(this, EditQuestionsActivity::class.java)
        intent.putExtra("survey", survey)
        intent.putExtra("Start", startDate)
        intent.putExtra("End", endDate)


        val format = SimpleDateFormat("dd/MM/yyyy")

        try {
            format.parse(startDate)
        } catch (e: Exception) {
            Toast.makeText(this, "Please enter a valid start date.", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            format.parse(endDate)
        } catch (e: Exception) {
            Toast.makeText(this, "Please enter a valid end date.", Toast.LENGTH_SHORT).show()
            return
        }

        when {
            startDate.isNullOrBlank() -> Toast.makeText(this, "Please enter a start date.", Toast.LENGTH_SHORT).show()
            endDate.isNullOrBlank() -> Toast.makeText(this, "Please enter an end date.", Toast.LENGTH_SHORT).show()
            format.parse(endDate) < format.parse(startDate) -> Toast.makeText(this, "End date must be after start date.", Toast.LENGTH_SHORT).show()

            else -> {
                startActivity(intent)
            }
        }
    }

    fun backBtn(view: View) {
        finish()
    }
}