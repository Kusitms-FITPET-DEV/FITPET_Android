package com.example.fitpet.ui.insurance.charge.finish

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCheckFinishBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeFinishFragment: BaseFragment<FragmentInsuranceChargeCheckFinishBinding, PageState.Default, InsuranceChargeFinishViewModel>(
    FragmentInsuranceChargeCheckFinishBinding::inflate
) {

    override val viewModel: InsuranceChargeFinishViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel

        binding.tbInsuranceCharge.btnTopBarInsuranceChargeBack.setOnClickListener { goBackToMyPetPage() }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeFinishEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeFinishEvent) {
        when (event) {
            InsuranceChargeFinishEvent.GoToMyPetPage -> goBackToMyPetPage()
            InsuranceChargeFinishEvent.GoToCompensationPage -> goToCompensationPage()
        }
    }

    private fun goBackToMyPetPage() {
        val action = InsuranceChargeFinishFragmentDirections.actionInsuranceFinishToMyPet()
        findNavController().navigate(action)
    }

    private fun goToCompensationPage() {
//        val action = InsuranceChargeFinishFragmentDirections.actionInsuranceFinishToCompensation()
//        findNavController().navigate(action)
    }
}