package com.example.fitpet.ui.insurance.compensation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceCompensationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceCompensationFragment :
    BaseFragment<FragmentInsuranceCompensationBinding, PageState.Default, InsuranceCompensationViewModel>(
        FragmentInsuranceCompensationBinding::inflate
    ) {

    override val viewModel: InsuranceCompensationViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
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