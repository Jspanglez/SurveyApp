package com.example.surveyapp.Model

data class Questions(val QuestionID: Int, val questionText: String, val SurveyID: Int ?= null) {
}