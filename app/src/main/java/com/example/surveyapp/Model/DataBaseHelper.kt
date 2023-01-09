package com.example.surveyapp.Model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/* Database Config*/
private val DataBaseName = "surveyAppDatabase.db"
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
    private val Column_SurveyTitle = "title"
    private val Column_StartDate = "startDate"
    private val Column_EndDate = "endDate"

    // Questions Table
    private val questionTable = "Questions"
    private val Column_QuestionID = "QuestionID"
    private val Column_QuestionText = "questionText"
    private val Column_ForeignSurveyID = "SurveyID"

    // Answers Table
    private val answerTable = "Answers"
    private val Column_AnswerID = "AnswerID"
    private val Column_AnswerText = "answerText"
    private val Column_ForeignUserID = "UserID"
    private val Column_ForeignQuestionID = "QuestionID"

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
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_QuestionText + " TEXT, " + Column_ForeignSurveyID +
                " INTEGER, " + "FOREIGN KEY ("+Column_ForeignSurveyID+") REFERENCES "+surveyTable+"("+Column_SurveyID+") ON DELETE CASCADE )"

            val sqlCreateStatementAnswers: String =
                "CREATE TABLE IF NOT EXISTS" + answerTable + " ( " + Column_AnswerID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_AnswerText + " TEXT, " + Column_ForeignUserID +
                " INTEGER, " + "FOREIGN KEY ("+Column_ForeignUserID+") REFERENCES "+userTable+"("+Column_UserID+") ON DELETE CASCADE )" +
                " INTEGER, " + "FOREIGN KEY ("+Column_ForeignQuestionID+") REFERENCES "+questionTable+"("+Column_QuestionID+") ON DELETE CASCADE )"

            db?.execSQL(sqlCreateStatementUser)
            db?.execSQL(sqlCreateStatementSurvey)
            db?.execSQL(sqlCreateStatementQuestions)
            db?.execSQL(sqlCreateStatementAnswers)
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

        try {
            db = this.readableDatabase
        }
        catch (e: SQLiteException) {
            return -2
        }

        val sqlStatement = "SELECT $Column_isAdmin FROM $userTable WHERE $Column_Username = '${user.username}'"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()) {
            // An admin is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -4
    }

    fun addSurvey(survey: Survey): Int {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_SurveyTitle, survey.title)
        cv.put(Column_StartDate, survey.startDate)
        cv.put(Column_EndDate, survey.endDate)

        val success = db.insert(surveyTable, null, cv)

        db.close()

        if (success.toInt() == -1)
            return success.toInt() //Error, adding new survey
        else

            return 1 //Add the survey
    }

    fun getSurvey(): ArrayList<Survey> {

        val db: SQLiteDatabase = this.readableDatabase
        val surveyList = ArrayList<Survey>()

        val sqlStatement = "SELECT * FROM $surveyTable"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val title: String = cursor.getString(1)
                val startDate: String = cursor.getString(2)
                val endDate: String = cursor.getString(3)

                val survey = Survey(id, title, startDate, endDate)
                surveyList.add(survey)

            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return surveyList
    }

    fun getSurveyID() : Int {
        val db: SQLiteDatabase = this.readableDatabase

        val sqlStatement = "SELECT $Column_SurveyID FROM $surveyTable WHERE $Column_SurveyID = (SELECT MAX($Column_SurveyID) FROM $surveyTable)"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()) {
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -4

    }

    fun addQuestions(questions: Questions): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_QuestionText, questions.questionText)
        cv.put(Column_ForeignSurveyID, questions.SurveyID)

        val success = db.insert(questionTable, null, cv)

        db.close()

        if (success.toInt() == -1)
            return success.toInt() //Error, adding new question
        else
            return 1 //Add the question
    }

    fun getQuestions(survey: Survey): ArrayList<String> {
        val db: SQLiteDatabase = this.readableDatabase
        val surveyQuestions = ArrayList<String>()
        val id = survey.SurveyID

        val sqlStatement = "SELECT $Column_QuestionText FROM $questionTable WHERE $Column_ForeignSurveyID == $id"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst())
            do {
                val q: String = cursor.getString(0)
                surveyQuestions.add(q)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return surveyQuestions
    }

    fun getQuestionID(survey: Survey): ArrayList<Int> {
        val db: SQLiteDatabase = this.readableDatabase
        val questionIDs = ArrayList<Int>()
        val id = survey.SurveyID

        val sqlStatement = "SELECT $Column_QuestionID FROM $questionTable WHERE $Column_ForeignSurveyID == $id"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst())
            do {
                val q: Int = cursor.getInt(0)
                questionIDs.add(q)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return questionIDs
    }

    fun addAnswers(answers: Answers): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_AnswerText, answers.answerText)
        cv.put(Column_ForeignUserID, answers.UserID)
        cv.put(Column_ForeignQuestionID, answers.QuestionID)

        val success = db.insert(answerTable, null, cv)

        db.close()

        if (success.toInt() == -1)
            return success.toInt() //Error, adding new question
        else
            return 1 //Add the question
    }

    fun updateSurvey(survey: Survey) : Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_StartDate, survey.startDate)
        cv.put(Column_EndDate, survey.endDate)

        val result = db.update(surveyTable, cv,"$Column_SurveyID = ${survey.SurveyID}", null) == 1
        db.close()
        return result
    }

    fun updateQuestion(questions: Questions) : Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_QuestionText, questions.questionText)

        val result = db.update(questionTable, cv,"$Column_QuestionID = ${questions.QuestionID}", null) == 1
        db.close()
        return result
    }

    fun deleteSurvey(survey: Survey) : Boolean {
        val db: SQLiteDatabase = this.writableDatabase

        val result = db.delete(surveyTable, "$Column_SurveyID = ${survey.SurveyID}", null) == 1

        db.close()
        return result
    }

    fun deleteQuestions(survey: Survey) : Boolean {
        val db: SQLiteDatabase = this.writableDatabase

        val result = db.delete(questionTable, "$Column_ForeignSurveyID = ${survey.SurveyID}", null) == 1

        db.close()
        return result
    }
}

