package com.example.fitpet.ui.registration.petBreed

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetBreedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PetBreedFragment : BaseFragment<FragmentPetBreedBinding, PetBreedPageState, PetBreedViewModel>(
    FragmentPetBreedBinding::inflate
) {

    override val viewModel: PetBreedViewModel by viewModels()

    override fun initView() {
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
            PetBreedEvent.GoToPetBreedDetail -> TODO()
        }
    }
}