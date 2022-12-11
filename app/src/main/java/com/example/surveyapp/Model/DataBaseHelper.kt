package com.example.surveyapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/* Database Config*/
private val DataBaseName = "surveyDatabase.db"
private val ver : Int = 1

class DataBaseHelper (context: Context) : SQLiteOpenHelper(context, DataBaseName, null, ver) {

    // User Table
    private val tableName = "Users"
    private val Column_UserID = "UserID"
    private val Column_Username = "username"
    private val Column_Password = "password"
    private val Column_isAdmin = "isAdmin"

    // Create the database
    override fun onCreate(db: SQLiteDatabase?) {

        try {
            val sqlCreateStatement: String = "CREATE TABLE IF NOT EXISTS" + tableName + " ( " + Column_UserID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Username + " TEXT NOT NULL UNIQUE, " +
                    Column_Password + " TEXT NOT NULL, " + Column_isAdmin + " INTEGER NOT NULL DEFAULT 0 )"

            db?.execSQL(sqlCreateStatement)
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

        val sqlStatement = "SELECT * FROM $tableName WHERE $Column_Username = ?"
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

        val success = db.insert(tableName, null, cv)

        db.close()

        if (success.toInt() == -1)
            return success.toInt() //Error, adding new user
        else
            return 1
    }




}

