package com.example.tasksandusers.util

class EmailValidator {

    companion object {

        fun isValid(email: String): Boolean {
            return email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$".toRegex())
        }
    }
}