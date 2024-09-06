package com.example.fitpet.model.request

data class RegisterPetRequest(
    val petName: String,
    val species: String,
    val breed: String,
    val birthYear: Int,
    val phone: String
)
