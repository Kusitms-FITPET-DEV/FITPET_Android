package com.example.fitpet.ui.insurance.charge.account

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeAccountFragment: BaseFragment<FragmentInsuranceChargeAccountBinding, PageState.Default, InsuranceChargeAccountViewModel>(
    FragmentInsuranceChargeAccountBinding::inflate
) {

    override val viewModel: InsuranceChargeAccountViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                // TODO 계좌 등록
            }
        }
    }
}