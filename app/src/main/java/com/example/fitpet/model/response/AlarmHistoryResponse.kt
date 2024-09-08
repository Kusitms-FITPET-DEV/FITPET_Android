package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class AlarmHistoryResponse (
    val historyList: List<HistoryItem>
) {
    data class HistoryItem(
        @SerializedName("historyId")
        val historyId: Int,
        @SerializedName("progress")
        val progress: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("confirmed")
        val confirmed: Boolean
    )
}