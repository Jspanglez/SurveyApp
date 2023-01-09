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

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun prev(view: View) {
        finish()
    }

    fun onClickButton(view: View) {
        val username = findViewById<EditText>(R.id.editTextNewUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextNewPassword).text.toString()
        val rePassword = findViewById<EditText>(R.id.editTextRePassword).text.toString()
        var admin: Boolean = false

        when {
            username.isNullOrBlank() -> Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
            password.isNullOrBlank() -> Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()
            rePassword.isNullOrBlank() -> Toast.makeText(this, "Please re-enter your password.", Toast.LENGTH_SHORT).show()
            password != rePassword -> Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()

            else -> {
                //Push username and password to the database
                val newUser = User(-1, username, password, admin)
                val myDatabase = DataBaseHelper(this)
                val result = myDatabase.addUser(newUser)

                when(result) {
                    1 -> {
                        Toast.makeText(this, "$username has successfully made an account.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    /*1 -> {
                        if (admin) {
                            Toast.makeText(this, "$username has successfully made an account.", Toast.LENGTH_SHORT).show()
                            startActivity(intentAdmin)
                        }

                        else if(!admin) {
                            Toast.makeText(this, "$username has successfully made an account.", Toast.LENGTH_SHORT).show()
                            startActivity(intentStudent)
                        }
                    }*/
                    -3 -> Toast.makeText(this, "The username '$username' already exists.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}