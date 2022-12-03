package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun login (view: View) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        fun signup (view: View) {
            val intent2 = Intent(this, SignUp::class.java)
            startActivity(intent2)
        }
    }
}