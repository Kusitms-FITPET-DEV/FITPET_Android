package com.example.fitpet.model.request

data class PetEditRequest(
    val petName: String,
    val species: String,
    val breed: String,
    val birthYear: Int
)