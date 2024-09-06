package com.example.fitpet.ui.insurance.charge.check

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCheckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeCheckFragment: BaseFragment<FragmentInsuranceChargeCheckBinding, PageState.Default, InsuranceChargeCheckViewModel>(
    FragmentInsuranceChargeCheckBinding::inflate
) {

    override val viewModel: InsuranceChargeCheckViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                // TODO 청구 확인 페이지
            }
        }
    }
}