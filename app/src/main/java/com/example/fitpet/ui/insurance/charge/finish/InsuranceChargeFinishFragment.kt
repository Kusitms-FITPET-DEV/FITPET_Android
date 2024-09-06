package com.example.fitpet.ui.insurance.charge.finish

import androidx.fragment.app.viewModels
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
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                // TODO 청구 확인 페이지
            }
        }
    }
}