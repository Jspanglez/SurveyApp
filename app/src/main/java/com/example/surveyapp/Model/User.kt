package com.example.surveyapp.Model

data class User(val userID: Int ?= null, val username: String, val password: String, var isAdmin: Boolean ?= null) {
}