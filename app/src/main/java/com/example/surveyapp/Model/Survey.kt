package com.example.surveyapp.Model
import java.io.Serializable

data class Survey(val SurveyID: Int ?= null, val title: String, val startDate: String, val endDate: String): Serializable {
}

