package com.example.fitpet.ui.registration.petDetailBreed

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetDetailBreedInputBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PetDetailBreedInputFragment : BaseFragment<FragmentPetDetailBreedInputBinding, PetDetailBreedInputPageState, PetDetailBreedInputViewModel>(
    FragmentPetDetailBreedInputBinding::inflate
) {

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
            PetDetailBreedInputEvent.GoToPetBirthInput -> TODO()
        }
    }
}