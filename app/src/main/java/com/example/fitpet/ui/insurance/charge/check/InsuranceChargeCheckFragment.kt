package com.example.fitpet.ui.insurance.charge.check

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCheckBinding
import com.example.fitpet.ui.insurance.charge.finish.InsuranceChargeCheckEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeCheckFragment: BaseFragment<FragmentInsuranceChargeCheckBinding, PageState.Default, InsuranceChargeCheckViewModel>(
    FragmentInsuranceChargeCheckBinding::inflate
) {

    override val viewModel: InsuranceChargeCheckViewModel by viewModels()
    private val arguments: InsuranceChargeCheckFragmentArgs by navArgs()

    override fun initView() {
        binding.viewModel = viewModel

        if (arguments.contactArgument.contactMethodEmail.isNotEmpty()) {
            binding.tvInsuranceChargeCheckContactMethod.text = "이메일"
            binding.tvInsuranceChargeCheckContactNumber.text = arguments.contactArgument.contactMethodEmail
        }
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
        val action = InsuranceChargeCheckFragmentDirections.actionInsuranceChargeCheckToFinish()
        findNavController().navigate(action)
    }
}