package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun onClickButton(view: View) {

        //val signup = findViewById<Button>(R.id.buttonSignUp)
        val username = findViewById<EditText>(R.id.editTextNewUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextNewPassword).text.toString()
        val rePassword = findViewById<EditText>(R.id.editTextRePassword).text.toString()
        val intent = Intent(this, StudentHomeActivity::class.java).apply {
        }

        when {
            username.isNullOrBlank() -> Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
            password.isNullOrBlank() -> Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()
            rePassword.isNullOrBlank() -> Toast.makeText(this, "Please re-enter your password.", Toast.LENGTH_SHORT).show()
            password != rePassword -> Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()

            else -> {
                Toast.makeText(this, "$username has successfully made an account.", Toast.LENGTH_SHORT).show()
                //Push username and password to the database

                startActivity(intent)
            }
        }
    }
}