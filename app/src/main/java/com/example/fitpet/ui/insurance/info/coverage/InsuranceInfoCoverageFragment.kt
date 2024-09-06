package com.example.fitpet.ui.insurance.info.coverage

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckCoverageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceInfoCoverageFragment: BaseFragment<FragmentInsuranceContractCheckCoverageBinding, PageState.Default, InsuranceInfoCoverageViewModel>(
    FragmentInsuranceContractCheckCoverageBinding::inflate
) {

    override val viewModel: InsuranceInfoCoverageViewModel by viewModels()

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                //
            }
        }
    }
}