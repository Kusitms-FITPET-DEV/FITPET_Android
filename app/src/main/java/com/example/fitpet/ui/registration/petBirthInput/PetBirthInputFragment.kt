package com.example.fitpet.ui.registration.petBirthInput

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetBirthInputBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PetBirthInputFragment : BaseFragment<FragmentPetBirthInputBinding, PetBirthInputPageState, PetBirthInputViewModel>(
    FragmentPetBirthInputBinding::inflate
) {
    override val viewModel: PetBirthInputViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
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
            PetBirthInputEvent.GoToContactInfoInput -> TODO()
        }
    }
}