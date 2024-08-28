package com.example.fitpet.ui.registration.petName

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetNameInputBinding
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

            }
        }
    }


}