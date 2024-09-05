package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class SearchPetBreedResponse(
    @SerializedName("searchCount")
    val searchCount: Int = -1,
    @SerializedName("searchedBreeds")
    val searchedBreeds: List<String> = emptyList()
)
