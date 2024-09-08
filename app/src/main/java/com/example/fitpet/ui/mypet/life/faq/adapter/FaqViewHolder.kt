package com.example.fitpet.ui.mypet.life.faq.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemFaqBinding
import com.example.fitpet.model.response.FaqItemVo

class FaqViewHolder(
    private val binding: ItemFaqBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FaqItemVo) {
        binding.apply {
            var isAnswerVisible = false

            question.text = item.question
            answer.text = item.answer

            btnToggle.setOnClickListener {
                isAnswerVisible = !isAnswerVisible
                if (isAnswerVisible) {
                    answerLayout.animate().alpha(1.0f).setDuration(300)
                        .withEndAction {
                            answerLayout.visibility = View.VISIBLE
                        }
                        .start()
                    btnToggle.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    answerLayout.animate().alpha(0.0f).setDuration(300)
                        .withEndAction {
                            answerLayout.visibility = View.GONE
                        }.start()
                    binding.btnToggle.setImageResource(R.drawable.ic_arrow_dorp_down)
                }
            }
        }
    }
}