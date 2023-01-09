package com.example.surveyapp

import android.icu.text.SimpleDateFormat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Survey
import com.example.surveyapp.Model.User

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.surveyapp", appContext.packageName)
    }

    @Test
    fun testAddUser() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val myDatabase = DataBaseHelper(appContext)
        val username = (0..Int.MAX_VALUE).random().toString()
        /**
         * The database helper is used to add a randomly created user
         *
         * If the user is successfully added, the function returns 1 and the test is passed
         */
        assertEquals(1, myDatabase.addUser(User(null, username, "Test", false)))
    }

    @Test
    fun testAddSurvey() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val myDatabase = DataBaseHelper(appContext)
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()).toString()
        val tomorrow = LocalDate.now().plusDays(1)
        val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        /**
         * The database helper is used to add a survey using today's and tomorrow's dates
         *
         * If the survey is successfully added, the function returns 1 and the test is passed
         */
        assertEquals(1, myDatabase.addSurvey(Survey(null, "Title", today, formattedTomorrow)))
    }
}