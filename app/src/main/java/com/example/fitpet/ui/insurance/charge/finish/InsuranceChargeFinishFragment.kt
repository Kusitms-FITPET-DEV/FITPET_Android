package com.example.fitpet.ui.insurance.charge.finish

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCheckFinishBinding
import com.example.fitpet.ui.insurance.charge.check.InsuranceChargeCheckFragmentArgs
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
        val arguments: InsuranceChargeFinishFragmentArgs by navArgs()
        val action = InsuranceChargeFinishFragmentDirections.actionInsuranceFinishToCompensation(arguments.petId)
        findNavController().navigate(action)
    }
}