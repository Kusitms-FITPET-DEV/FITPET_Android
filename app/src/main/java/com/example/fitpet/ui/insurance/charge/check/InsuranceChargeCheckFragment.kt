package com.example.fitpet.ui.insurance.charge.check

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCheckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeCheckFragment: BaseFragment<FragmentInsuranceChargeCheckBinding, InsuranceChargeCheckPageState, InsuranceChargeCheckViewModel>(
    FragmentInsuranceChargeCheckBinding::inflate
) {

    override val viewModel: InsuranceChargeCheckViewModel by viewModels()
    private val arguments: InsuranceChargeCheckFragmentArgs by navArgs()

    override fun initView() {
        binding.viewModel = viewModel
        binding.tbInsuranceCharge.btnTopBarInsuranceChargeBack.setOnClickListener { findNavController().popBackStack() }

        viewModel.setInsuranceData(arguments.insuranceArgument)
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeCheckEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeCheckEvent) {
        when (event) {
            InsuranceChargeCheckEvent.GoToFinishPage -> goToFinishPage()
        }
    }

    private fun goToFinishPage() {
        val action = InsuranceChargeCheckFragmentDirections.actionInsuranceChargeCheckToFinish(arguments.insuranceArgument.petId)
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentInsuranceChargeCause, true)
            .build()

        findNavController().navigate(action, navOptions)
    }
}