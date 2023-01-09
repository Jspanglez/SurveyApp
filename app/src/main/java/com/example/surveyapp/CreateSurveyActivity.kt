package com.example.surveyapp

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CreateSurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_survey)

        val startDate = findViewById<EditText>(R.id.editTextStartDate)
        val endDate = findViewById<EditText>(R.id.editTextEndDate)

        //From stackoverflow
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val tomorrow = LocalDate.now().plusDays(1)
        val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        startDate.setText(today)
        endDate.setText(formattedTomorrow)

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