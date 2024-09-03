package com.example.fitpet.ui.insurance.charge.account

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeAccountFragment: BaseFragment<FragmentInsuranceChargeAccountBinding, InsuranceChargeAccountPageState, InsuranceChargeAccountViewModel>(
    FragmentInsuranceChargeAccountBinding::inflate
) {

    override val viewModel: InsuranceChargeAccountViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeAccountEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeAccountEvent) {
        when (event) {
            InsuranceChargeAccountEvent.ClickBankInput -> showBankList()
        }
    }

    private fun showBankList() {
        //
    }
}