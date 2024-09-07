package com.example.fitpet.model.domain

import com.google.gson.annotations.SerializedName

data class QuizVo(
    @SerializedName("question")
    val question: String = "",
    @SerializedName("answer")
    val answer: Boolean = true,
    @SerializedName("description")
    val description: String = ""
)
