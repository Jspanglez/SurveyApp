package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login =  findViewById<Button>(R.id.buttonLogin)
        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)

        login.setOnClickListener {

            if(username.text.isNullOrBlank()||password.text.isNullOrBlank()) {
                Toast.makeText(this, "Please fill in the required fields.", Toast.LENGTH_SHORT).show()
            }

            /*else if(user does not exist) {
                Toast = "User doesn't exist."
            }*/

            /*else if(password != user's password) {
                Toast = "Password is incorrect."
            }*/

            else {
                Toast.makeText(this, "${username.text} has successfully logged in.", Toast.LENGTH_SHORT).show()
                //Intent stuff goes here
            }
        }
    }
}