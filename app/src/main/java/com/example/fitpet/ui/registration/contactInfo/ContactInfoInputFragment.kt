package com.example.fitpet.ui.registration.contactInfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentContactInfoInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ContactInfoInputFragment : BaseFragment<FragmentContactInfoInputBinding, ContactInfoInputPageState, ContactInfoInputViewModel>(
    FragmentContactInfoInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: ContactInfoInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
            contactInfoEditText.addTextChangedListener(object : TextWatcher {
                private var isUpdating = false

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (isUpdating) return

                    val input = s?.toString() ?: ""
                    val digitsOnly = input.replace(Regex("\\D"), "")

                    if (digitsOnly.length > 11) return

                    val formattedNumber = when {
                        digitsOnly.length > 6 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3, 7)}-${digitsOnly.substring(7)}"
                        digitsOnly.length > 3 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3)}"
                        else -> digitsOnly
                    }

                    if (input != formattedNumber) {
                        isUpdating = true
                        contactInfoEditText.setText(formattedNumber)
                        contactInfoEditText.setSelection(formattedNumber.length)
                        isUpdating = false
                    }

                    viewModel.onTextChanged(formattedNumber)
                }

                override fun afterTextChanged(s: Editable?) {}
            })
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
            ContactInfoInputEvent.GoToMyPetInsurance -> goToMyPetInsurance()
            ContactInfoInputEvent.ShowSkipDialog -> showSkipDialog()
        }
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {}
        )
    }

    private fun goToMyPetInsurance() {
        val action = ContactInfoInputFragmentDirections.actionContactInfoInputToMyPet()
        findNavController().navigate(action)
    }
}