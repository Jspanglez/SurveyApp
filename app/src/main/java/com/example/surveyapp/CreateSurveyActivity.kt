package com.example.surveyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class CreateSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_survey)

        var spinner = findViewById<Spinner>(R.id.spinner)
        val moduleCodes = arrayOf("CTEC3911", "CTEC3905", "CTEC3906", "IMAT3423", "CTEC3451")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, moduleCodes)
        spinner.adapter = arrayAdapter
    }

    fun nextBtn(view: View) {
        val mySpinner = findViewById<Spinner>(R.id.spinner)
        val surveyName = mySpinner.selectedItem.toString()
        val startDate = findViewById<EditText>(R.id.editTextStartDate).text.toString()
        val endDate = findViewById<EditText>(R.id.editTextEndDate).text.toString()
        val intent = Intent(this, CreateQuestionsActivity::class.java)
        intent.putExtra("Survey Name", surveyName)
        intent.putExtra("Start", startDate)
        intent.putExtra("End", endDate)
        when {
            /*startDate.isNullOrBlank() -> Toast.makeText(this, "Please enter a start date.", Toast.LENGTH_SHORT).show()
            endDate.isNullOrBlank() -> Toast.makeText(this, "Please enter an end date.", Toast.LENGTH_SHORT).show()
            !(startDate[1].equals("/")) || !(startDate[2].equals("/")) -> Toast.makeText(this, "Please enter a valid start date. (1)", Toast.LENGTH_SHORT).show()
            !(startDate[3].equals("/") || startDate[4].equals("/") || startDate[5].equals("/"))  -> Toast.makeText(this, "Please enter a valid start date. (2)", Toast.LENGTH_SHORT).show()

            !(endDate[1].equals("/") || endDate[2].equals("/")) && !(endDate[3].equals("/") || endDate[4].equals("/") ||
                    endDate[5].equals("/"))  -> Toast.makeText(this, "Please enter a valid end date.", Toast.LENGTH_SHORT).show()*/

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