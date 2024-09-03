package com.example.fitpet.ui.insurance.charge.account

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

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
                // TODO 계좌 등록

                viewModel.uiState.accountOwner.collect {
                    Timber.d("[테스트] 예금주 -> $it")
                }
            }
            launch {
                viewModel.uiState.accountNumber.collect {
                    Timber.d("[테스트] 계좌번호 -> $it")
                }
            }
        }
    }
}