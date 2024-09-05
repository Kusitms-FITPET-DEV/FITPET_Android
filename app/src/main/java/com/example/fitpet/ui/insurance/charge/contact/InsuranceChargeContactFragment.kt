package com.example.fitpet.ui.insurance.charge.contact

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeContactBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeContactFragment: BaseFragment<FragmentInsuranceChargeContactBinding, PageState.Default, InsuranceChargeContactViewModel>(
    FragmentInsuranceChargeContactBinding::inflate
) {

    override val viewModel: InsuranceChargeContactViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                // TODO 연락 수단 등록
            }
        }
    }
}