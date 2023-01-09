package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.User

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun backToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun loginButton(view: View) {

        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        val intentStudent = Intent(this, StudentHomeActivity::class.java).apply {
        }
        val intentAdmin = Intent(this, AdminHomeActivity::class.java).apply {
        }

        when {
            username.isNullOrBlank() -> Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
            password.isNullOrBlank() -> Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()

            else -> {
                val myDatabase = DataBaseHelper(this)
                val result = myDatabase.getUser(User(-1, username, password, false))
                val admin = myDatabase.getAdmin(User(-1, username, password, false))

                when(result) {
                    -1 -> Toast.makeText(this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show()

                    else -> {

                        when(admin) {
                            1 -> {
                                Toast.makeText(this, "$username has successfully logged in.", Toast.LENGTH_SHORT).show()
                                startActivity(intentAdmin)
                            }

                            0 -> {
                                Toast.makeText(this, "$username has successfully logged in.", Toast.LENGTH_SHORT).show()
                                startActivity(intentStudent)
                            }

                            -2 -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}