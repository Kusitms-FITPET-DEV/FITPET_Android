package com.example.fitpet.ui.mypet.life.faq.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemFaqBinding
import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo

class FaqViewHolder(
    private val binding: ItemFaqBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: FaqItemVo) {
        val context = binding.root.context

        binding.apply {
            var isAnswerVisible = false

            question.text = item.question
            answer.text = item.answer

            titleLayout.setOnClickListener {
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

            when(item.type) {
                FaqType.REWARD -> tag.text = "# ${context.getString(R.string.reward)}"
                FaqType.INSURANCE_FEE -> tag.text = "# ${context.getString(R.string.insurance_fee)}"
                FaqType.COVERAGE_RANGE -> tag.text = "# ${context.getString(R.string.coverage_range)}"
                FaqType.COVERAGE_PERIOD -> tag.text = "# ${context.getString(R.string.coverage_period)}"
                FaqType.SUBSCRIPTION_TERM -> tag.text = "# ${context.getString(R.string.subscription_term)}"
                FaqType.OTHER_INFO -> tag.text = "# ${context.getString(R.string.other_info)}"
            }
        }
    }
}