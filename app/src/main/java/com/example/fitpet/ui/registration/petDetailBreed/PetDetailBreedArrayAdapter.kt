package com.example.fitpet.ui.registration.petDetailBreed

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fitpet.R

class PetDetailBreedArrayAdapter(
    context: Context,
    private val items: List<String>,
    private val query: String
) : ArrayAdapter<String>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_pet_detail_breed, parent, false)
        val textView = view.findViewById<TextView>(R.id.text1)
        val item = getItem(position) ?: ""
        val spannableString = SpannableString(item)
        val startIndex = item.indexOf(query, ignoreCase = true)

        if (startIndex != -1) {
            val endIndex = startIndex + query.length
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.point_blue)),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        textView.text = spannableString
        return view
    }
}