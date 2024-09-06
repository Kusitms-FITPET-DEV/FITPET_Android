package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class ChargeUploadResponse (
    @SerializedName("additionalProp1")
    val additionalProp1: String,
    @SerializedName("additionalProp2")
    val additionalProp2: String,
    @SerializedName("additionalProp3")
    val additionalProp3: String
)