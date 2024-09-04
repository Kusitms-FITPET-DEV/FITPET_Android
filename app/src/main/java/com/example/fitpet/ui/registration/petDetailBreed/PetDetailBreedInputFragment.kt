package com.example.fitpet.ui.registration.petDetailBreed

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetDetailBreedInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PetDetailBreedInputFragment : BaseFragment<FragmentPetDetailBreedInputBinding, PetDetailBreedInputPageState, PetDetailBreedInputViewModel>(
    FragmentPetDetailBreedInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetDetailBreedInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetDetailBreedInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetDetailBreedInputEvent) {
        when(event) {
            PetDetailBreedInputEvent.GoToPetBirthInput -> goToPetBirthInput()
            PetDetailBreedInputEvent.ShowSkipDialog -> showSkipDialog()
        }
    }

    private fun goToPetBirthInput() {
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToPetBirthInput()
        findNavController().navigate(action)
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {}
        )
    }

    private fun goToMyPetInsurance() {
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToMyPet()
        findNavController().navigate(action)
    }
}