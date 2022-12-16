package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CreateQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_questions)
    }

    fun createBtn(view: View) {
        //Pop-up warning: "Are you sure?"

        //Push questions to database



        val intent = Intent(this, AdminHomeActivity::class.java)
        startActivity(intent)
    }
}