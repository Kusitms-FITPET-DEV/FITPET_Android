package com.example.fitpet.ui.mypet.insurance.petEdit.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemMyPetBottomBinding
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.util.ItemDiffCallback

class MyPetRVA(
    val onEditClicked: (PetInfo) -> Unit,
    val onClicked: (PetInfo) -> Unit) : ListAdapter<PetInfo, MyPetRVA.ListViewHolder>(
    ItemDiffCallback<PetInfo>(
        onItemsTheSame = { oldItem, newItem -> oldItem.petId == newItem.petId },  // ID로 항목 비교
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }      // 객체 내용 비교
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemMyPetBottomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pet = getItem(position)
        holder.bind(pet)
    }

    inner class ListViewHolder(private val binding: ItemMyPetBottomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: PetInfo){
            with(binding){
                tvItemMyPetName.text = pet.name
                setInsuranceCompany(pet.species, ivIconPetSpecies)
                ivEditIcon.setOnClickListener {
                    onEditClicked(pet)
                }
                root.setOnClickListener {
                    onClicked(pet)
                }
            }
        }
    }

    fun setInsuranceCompany(company : String, view : ImageView){
        var drawable: Drawable? = null
        when(company){
            DOG -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_mypet_dog)
            }
            CAT -> {
                drawable = ContextCompat.getDrawable(view.context, R.drawable.ic_mypet_cat)
            }
            else -> null
        }
        view.setImageDrawable(drawable)
    }

    companion object {
        const val DOG = "DOG"
        const val CAT = "CAT"
    }
}