package com.example.fitpet.ui.mypet.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemNoRegisterInsuranceBinding
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterEvent
import com.example.fitpet.util.ItemDiffCallback
import com.example.fitpet.util.ResourceProvider
import org.w3c.dom.Text
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class InsuranceNoRegisterRVA(
    val resourceProvider: ResourceProvider,
    val onClicked: (InsuranceSuggestion) -> Unit) : ListAdapter<InsuranceSuggestion, InsuranceNoRegisterRVA.ListViewHolder>(
    ItemDiffCallback<InsuranceSuggestion>(
        onItemsTheSame = { oldItem, newItem -> oldItem.priceId == newItem.priceId },  // ID로 항목 비교
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }      // 객체 내용 비교
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemNoRegisterInsuranceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val insurance = getItem(position)
        holder.bind(insurance)
    }

    inner class ListViewHolder(private val binding: ItemNoRegisterInsuranceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(insurance: InsuranceSuggestion){
            with(binding){
                tvItemInsuranceName.text = insurance.insuranceName
                tvItemInsurancePrice.text = NumberFormat.getNumberInstance(Locale.US).format(insurance.insuranceFee).toString()
                setInsuranceCompany(insurance.insuranceCompany, ivItemInsuranceCompany, tvItemInsuranceCompany)
                root.setOnClickListener {
                    onClicked(insurance)
                }
            }
        }
    }

    fun setInsuranceCompany(company : String, view : ImageView, tv : TextView){
        var drawable: Drawable? = null
        when(company){
            "db" -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_insurance_db)
                tv.text = resourceProvider.getString(R.string.db)
            }
            "kb" -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_insurance_kb)
                tv.text = resourceProvider.getString(R.string.kb)
            }
            "hyundai" -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_insurance_hyundai)
                tv.text = resourceProvider.getString(R.string.hyundai)
            }
            "samsung" -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_insurance_samsung)
                tv.text = resourceProvider.getString(R.string.samsung)
            }
            "meritz" -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_insurance_meritz)
                tv.text = resourceProvider.getString(R.string.meritz)
            }
            else -> null
        }
        view.setImageDrawable(drawable)
    }
}