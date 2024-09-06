package com.example.fitpet.ui.insurance.info.coverage

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckCoverageBinding
import com.example.fitpet.ui.insurance.info.InsuranceContractCheckViewModel.Companion.COVERAGE
import com.example.fitpet.ui.model.InsuranceContractInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceInfoCoverageFragment: BaseFragment<FragmentInsuranceContractCheckCoverageBinding, InsuranceCoveragePageState, InsuranceInfoCoverageViewModel>(
    FragmentInsuranceContractCheckCoverageBinding::inflate
) {

    override val viewModel: InsuranceInfoCoverageViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel

        getCoverageData()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                //
            }
        }
    }

    private fun getCoverageData() {
        arguments?.let { bundle ->
            val coverage = bundle.getParcelable<InsuranceContractInfo.Coverage>(COVERAGE)
            coverage?.let {
                viewModel.setCoverageData(it)
            }
        }
    }
}