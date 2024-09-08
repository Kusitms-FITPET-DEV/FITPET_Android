package com.example.fitpet.ui.registration.petBreed

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetBreedBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PetBreedFragment : BaseFragment<FragmentPetBreedBinding, PetBreedPageState, PetBreedViewModel>(
    FragmentPetBreedBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetBreedViewModel by viewModels()
    private var petName = ""

    override fun initView() {
        val args: PetBreedFragmentArgs by navArgs()
        petName = args.petName
        binding.apply {
            vm = viewModel
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetBreedEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetBreedEvent) {
        when (event) {
            PetBreedEvent.GoToPetBreedDetail -> goToPetDetailBreedInput()
            PetBreedEvent.ShowSkipDialog -> showSkipDialog()
        }
    }

    private fun goToPetDetailBreedInput() {
        val petBreed = viewModel.uiState.breed.value.name
        val action = PetBreedFragmentDirections.actionPetBreedToPetDetailBreedInput(petName = petName, petBreed = petBreed)
        findNavController().navigate(action)
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
        val action = PetBreedFragmentDirections.actionPetBreedToMyPet()
        findNavController().navigate(action)
    }
}