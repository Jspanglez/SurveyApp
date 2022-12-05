package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*login.setOnClickListener {
            when {
                username.text.isNullOrBlank() -> Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
                password.text.isNullOrBlank() -> Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()
                //password.text != user's password -> Toast.makeText(this, "Password is incorrect.", Toast.LENGTH_SHORT).show()
                //username is not in database -> Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show()

                else -> {
                    Toast.makeText(this, "${username.text} has successfully logged in.", Toast.LENGTH_SHORT).show()
                    fun logInButton(view: View) {
                        val intent = Intent(this, StudentHomeActivity::class.java).apply {
                            startActivity(intent)
                        }
                    }
                }
            }
        }*/
    }

    fun loginButton(view: View) {

        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val intent = Intent(this, StudentHomeActivity::class.java).apply {
        }

        when {
            username.text.isNullOrBlank() -> Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
            password.text.isNullOrBlank() -> Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()
            //password.text != user's password -> Toast.makeText(this, "Password is incorrect.", Toast.LENGTH_SHORT).show()
            //username is not in database -> Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show()

            else -> {
                Toast.makeText(this, "${username.text} has successfully logged in.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
}