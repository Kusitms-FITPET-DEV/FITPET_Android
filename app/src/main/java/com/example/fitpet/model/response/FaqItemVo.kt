package com.example.fitpet.model.response

import com.example.fitpet.model.domain.FaqType
import com.google.gson.annotations.SerializedName

data class FaqItemVo(
    @SerializedName("type")
    val type: FaqType = FaqType.REWARD,
    @SerializedName("question")
    val question: String = "",
    @SerializedName("answer")
    val answer: String = ""
)
