package com.example.fitpet.ui.registration.petName

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetNameInputBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PetNameInputFragment : BaseFragment<FragmentPetNameInputBinding, PetNameInputPageState, PetNameInputViewModel>(
    FragmentPetNameInputBinding::inflate
) {

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
        }
    }

    private fun goToPetBreedInput() {
        val action = PetNameInputFragmentDirections.actionPetNameInputToPetBreed()
        findNavController().navigate(action)
    }
}