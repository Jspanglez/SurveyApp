package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.surveyapp.Model.Answers
import com.example.surveyapp.Model.DataBaseHelper

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
    }

    fun no(view: View) {
        finish()
    }

    fun yes(view: View) {
        val answers = intent.getStringArrayListExtra("Answers")
        val myDatabase = DataBaseHelper(this)

        if (answers != null) {
            for (x in answers) {
                val newAnswer = Answers(1, x, 1, 1)
                myDatabase.addAnswers(newAnswer)
            }
        }

        Toast.makeText(this, "Answers have been submitted.", Toast.LENGTH_SHORT).show()
        val home = Intent(this, StudentHomeActivity::class.java)
        startActivity(home)
    }
}