package com.example.fitpet.ui.registration.contactInfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentContactInfoInputBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactInfoInputFragment : BaseFragment<FragmentContactInfoInputBinding, ContactInfoInputPageState, ContactInfoInputViewModel>(
    FragmentContactInfoInputBinding::inflate
) {

    override val viewModel: ContactInfoInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as ContactInfoInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: ContactInfoInputEvent) {
        when(event) {
            ContactInfoInputEvent.GoToMyPetInsurance -> TODO()
        }
    }
}