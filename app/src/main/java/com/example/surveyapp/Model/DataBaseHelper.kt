package com.example.surveyapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.TableLayout

/* Database Config*/
private val DataBaseName = "surveyDatabase.db"
private val ver : Int = 1

class DataBaseHelper (context: Context) : SQLiteOpenHelper(context, DataBaseName, null, ver) {

    // User Table
    private val userTable = "Users"
    private val Column_UserID = "UserID"
    private val Column_Username = "username"
    private val Column_Password = "password"
    private val Column_isAdmin = "isAdmin"

    // Survey Table
    private val surveyTable = "Surveys"
    private val Column_SurveyID = "SurveyID"
    private val Column_SurveyTitle = "surveyTitle"
    private val Column_StartDate = "startDate"
    private val Column_EndDate = "endDate"

    // Questions Table
    private val questionTable = "Questions"
    private val Column_QuestionID = "QuestionID"
    private val Column_QuestionText = "questionText"

    // Answers Table
    private val answerTable = "Answers"
    private val Column_AnswerID = "AnswerID"
    private val Column_AnswerText = "answerText"

    // Create the database
    override fun onCreate(db: SQLiteDatabase?) {

        try {
            val sqlCreateStatementUser: String =
                "CREATE TABLE IF NOT EXISTS" + userTable + " ( " + Column_UserID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Username + " TEXT NOT NULL UNIQUE, " +
                Column_Password + " TEXT NOT NULL, " + Column_isAdmin + " INTEGER NOT NULL DEFAULT 0 )"

            val sqlCreateStatementSurvey: String =
                "CREATE TABLE IF NOT EXISTS" + surveyTable + " ( " + Column_SurveyID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_SurveyTitle + " TEXT NOT NULL, " +
                Column_StartDate + " TEXT NOT NULL, " + Column_EndDate + " TEXT )"

            val sqlCreateStatementQuestions: String =
                "CREATE TABLE IF NOT EXISTS" + questionTable + " ( " + Column_QuestionID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_QuestionText + " TEXT, " + Column_SurveyID +
                " INTEGER FOREIGN KEY )"

            db?.execSQL(sqlCreateStatementUser)
            db?.execSQL(sqlCreateStatementSurvey)
            db?.execSQL(sqlCreateStatementQuestions)
        }
        catch (e: SQLiteException) {}
    }

    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    private fun checkUsername(user: User): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val username = user.username.lowercase()

        val sqlStatement = "SELECT * FROM $userTable WHERE $Column_Username = ?"
        val param = arrayOf(username)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // Error: the username already exists
        }

        cursor.close()
        db.close()
        return 0 //User not found

    }

    fun addUser (user: User) : Int {

        val doesUsernameExist = checkUsername(user)
        if(doesUsernameExist < 0)
            return doesUsernameExist

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_Username, user.username.lowercase())
        cv.put(Column_Password, user.password)
        cv.put(Column_isAdmin, user.isAdmin)

        val success = db.insert(userTable, null, cv)

        db.close()

        if (success.toInt() == -1)
            return success.toInt() //Error, adding new user
        else
            return 1 //Add the user
    }

    fun getUser(user: User): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch (e: SQLiteException) {
            return -2
        }

        val userName = user.username.lowercase()
        val userPassword = user.password

        val sqlStatement = "SELECT * FROM $userTable WHERE $Column_Username = ? AND $Column_Password = ?"
        val param = arrayOf(userName, userPassword)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if(cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1 //The user is not found

    }

    fun getAdmin(user: User): Int {
        val db: SQLiteDatabase
        val isAdmin = user.isAdmin

        try {
            db = this.readableDatabase
        }
        catch (e: SQLiteException) {
            return -2
        }

        val sqlStatement = "SELECT * FROM $userTable WHERE $Column_isAdmin = 1"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()) {
            // An admin is found
            //val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return 1
        }

        cursor.close()
        db.close()
        return -4
    }

    fun getStudent(user: User): Int {
        val db: SQLiteDatabase
        val isAdmin = user.isAdmin

        try {
            db = this.readableDatabase
        }
        catch (e: SQLiteException) {
            return -2
        }

        val sqlStatement = "SELECT * FROM $userTable WHERE $Column_isAdmin = 0"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()) {
            // A student is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return 2
        }

        cursor.close()
        db.close()
        return -4
    }

}

