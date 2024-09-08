package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class PetResponse(
    @SerializedName("petCount") var petCount: Int,
    @SerializedName("petList") val petList: List<PetInfo>
)

data class PetInfo(
    @SerializedName("petId") val petId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthYear") val birthYear: Int,
    @SerializedName("age") val age: Int,
    @SerializedName("species") val species: String,
    @SerializedName("breed") val breed: String
)
