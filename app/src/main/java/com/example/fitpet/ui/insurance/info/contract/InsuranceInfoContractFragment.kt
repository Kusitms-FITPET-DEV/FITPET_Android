package com.example.fitpet.ui.insurance.info.contract

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckContractBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceInfoContractFragment : BaseFragment<FragmentInsuranceContractCheckContractBinding, PageState.Default, InsuranceInfoContractViewModel>(
    FragmentInsuranceContractCheckContractBinding::inflate
) {

    override val viewModel: InsuranceInfoContractViewModel by viewModels()

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