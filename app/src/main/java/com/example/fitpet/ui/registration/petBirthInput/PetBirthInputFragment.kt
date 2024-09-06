package com.example.fitpet.ui.registration.petBirthInput

import android.text.InputFilter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetBirthInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PetBirthInputFragment : BaseFragment<FragmentPetBirthInputBinding, PetBirthInputPageState, PetBirthInputViewModel>(
    FragmentPetBirthInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetBirthInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
//            birthInputEditText.filters = arrayOf(InputFilter.LengthFilter(4))
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetBirthInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetBirthInputEvent) {
        when(event) {
            PetBirthInputEvent.GoToContactInfoInput -> goToContactInfoInput()
            PetBirthInputEvent.ShowSkipDialog -> showSkipDialog()
        }
    }

    private fun goToContactInfoInput() {
        val action = PetBirthInputFragmentDirections.actionPetBirthInputToContactInfoInput()
        findNavController().navigate(action)
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {}
        )
    }

    private fun goToMyPetInsurance() {
        val action = PetBirthInputFragmentDirections.actionPetBirthInputToMyPet()
        findNavController().navigate(action)
    }
}