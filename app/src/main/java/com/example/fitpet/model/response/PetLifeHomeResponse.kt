package com.example.fitpet.model.response

import com.example.fitpet.model.domain.JournalItemVo
import com.example.fitpet.model.domain.QuizVo
import com.google.gson.annotations.SerializedName
import java.io.Serial

data class PetLifeHomeResponse(
    @SerializedName("journalList")
    val journalList: List<JournalItemVo> = emptyList(),
    @SerializedName("quiz")
    val quiz: QuizVo = QuizVo(),
    @SerializedName("questionList")
    val questionList: List<String> = emptyList()
)
