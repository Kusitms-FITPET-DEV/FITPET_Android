package com.example.fitpet.ui.registration.contactInfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R

class ContactInfoInputFragment : Fragment() {

    companion object {
        fun newInstance() = ContactInfoInputFragment()
    }

    private val viewModel: ContactInfoInputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_contact_info_input, container, false)
    }
}