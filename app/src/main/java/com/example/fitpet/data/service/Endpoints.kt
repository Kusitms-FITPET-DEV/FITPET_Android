package com.example.fitpet.data.service

object Endpoints {

    object Auth {
        private const val AUTH = "/auth"
        const val LOGIN = "$AUTH/login"
        const val LOGOUT = "$AUTH/logout"
        const val REFRESH = "$AUTH/refresh"
    }

    object Pets {
        const val PETS = "/pets"
        const val SEARCH = "$PETS/breeds/search"
    }

    object PetLife{
        const val HOME = "/home"
        const val JOURNAL = "/journal"
        const val QUESTIONS = "/questions"
    }

    object Charge {
        const val CHARGE = "/charge"
        const val UPLOAD = "$CHARGE/upload"
    }
}