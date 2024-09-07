package com.example.fitpet.ui.insurance.compensation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceCompensationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceCompensationFragment :
    BaseFragment<FragmentInsuranceCompensationBinding, InsuranceCompensationPageState, InsuranceCompensationViewModel>(
        FragmentInsuranceCompensationBinding::inflate
    ) {

    override val viewModel: InsuranceCompensationViewModel by viewModels()
    val arguments: InsuranceCompensationFragmentArgs by navArgs()

    override fun initView() {
        binding.viewModel = viewModel

        viewModel.getCompensationList(arguments.petId)
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceCompensationEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceCompensationEvent) {
        when (event) {
            InsuranceCompensationEvent.GoBackPage -> goBackPage()
        }
    }

    private fun goBackPage() {
        findNavController().popBackStack()
    }
}