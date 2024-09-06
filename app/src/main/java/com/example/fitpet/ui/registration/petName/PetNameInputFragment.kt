package com.example.fitpet.ui.registration.petName

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetNameInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PetNameInputFragment : BaseFragment<FragmentPetNameInputBinding, PetNameInputPageState, PetNameInputViewModel>(
    FragmentPetNameInputBinding::inflate
) {

    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetNameInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetNameInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetNameInputEvent) {
        when (event) {
            PetNameInputEvent.GoToPetBreedInput -> goToPetBreedInput()
            PetNameInputEvent.ShowSkipDialog -> showSkipDialog()
        }
    }

    private fun goToPetBreedInput() {
        val petName = viewModel.uiState.petName.value
        val action = PetNameInputFragmentDirections.actionPetNameInputToPetBreed(petName)
        findNavController().navigate(action)
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {}
        )
    }

    private fun goToMyPetInsurance() {
        val action = PetNameInputFragmentDirections.actionPetNameInputToMyPet()
        findNavController().navigate(action)
    }
}