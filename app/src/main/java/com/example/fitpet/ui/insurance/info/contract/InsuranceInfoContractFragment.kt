package com.example.fitpet.ui.insurance.info.contract

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckContractBinding
import com.example.fitpet.ui.insurance.info.InsuranceContractCheckViewModel.Companion.CONTRACT
import com.example.fitpet.ui.model.InsuranceContractInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceInfoContractFragment : BaseFragment<FragmentInsuranceContractCheckContractBinding, InsuranceContractPageState , InsuranceInfoContractViewModel>(
    FragmentInsuranceContractCheckContractBinding::inflate
) {

    override val viewModel: InsuranceInfoContractViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel

        getContractData()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                //
            }
        }
    }

    private fun getContractData() {
        arguments?.let { bundle ->
            val contract = bundle.getParcelable<InsuranceContractInfo.Contract>(CONTRACT)
            contract?.let {
                viewModel.setContractData(it)
            }
        }
    }
}