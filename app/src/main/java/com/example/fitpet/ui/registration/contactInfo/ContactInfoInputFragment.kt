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
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentContactInfoInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ContactInfoInputFragment : BaseFragment<FragmentContactInfoInputBinding, ContactInfoInputPageState, ContactInfoInputViewModel>(
    FragmentContactInfoInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: ContactInfoInputViewModel by viewModels()

    private var petName = ""
    private var petBreed = ""
    private var petDetailBreed = ""
    private var petBirth = ""

    override fun initView() {
        binding.apply {
            vm = viewModel
            val args: ContactInfoInputFragmentArgs by navArgs()
            petName = args.petName
            petBreed = args.petBreed
            petDetailBreed = args.petDetailBreed
            petBirth = args.petBirth

            Timber.e("petName: $petName, petBreed: $petBreed, petDetailBreed: $petDetailBreed, petBirth: $petBirth")

            contactInfoEditText.addTextChangedListener(object : TextWatcher {
                private var isUpdating = false
                private var lastInput = ""

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    lastInput = s?.toString() ?: ""
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (isUpdating) return

                    val input = s?.toString() ?: ""
                    val digitsOnly = input.replace(Regex("\\D"), "")

                    if (digitsOnly.length > 11) return

                    isUpdating = true

                    if (before > 0 && count == 0) {
                        if (lastInput.endsWith("-") && input == lastInput.dropLast(1)) {
                            contactInfoEditText.setText(input.dropLast(1))
                            contactInfoEditText.setSelection(input.length - 1)
                            isUpdating = false
                            return
                        }
                    }

                    val formattedNumber = when {
                        digitsOnly.length > 6 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3, 7)}-${digitsOnly.substring(7)}"
                        digitsOnly.length > 3 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3)}"
                        else -> digitsOnly
                    }

                    if (input != formattedNumber) {
                        contactInfoEditText.setText(formattedNumber)
                        contactInfoEditText.setSelection(formattedNumber.length)
                    }
                    isUpdating = false

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
            ContactInfoInputEvent.RegisterPet -> registerPet()
        }
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.stop_registration_title),
            contentText = requireContext().getString(R.string.stop_registration_content),
            iconResourceId = R.drawable.ic_dog_sad,
            skipVisibility = true
        )
    }

    private fun goToMyPetInsurance() {
        val action = ContactInfoInputFragmentDirections.actionContactInfoInputToMyPet()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()
        findNavController().navigate(action, navOptions)
    }

    private fun registerPet() {
        viewModel.registerPet(
            petName, petBreed, petDetailBreed, petBirth
        )
    }
}