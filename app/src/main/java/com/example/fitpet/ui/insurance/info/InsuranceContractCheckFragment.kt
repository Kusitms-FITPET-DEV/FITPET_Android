package com.example.fitpet.ui.insurance.info

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceContractCheckFragment: BaseFragment<FragmentInsuranceContractCheckBinding, PageState.Default, InsuranceContractCheckViewModel>(
    FragmentInsuranceContractCheckBinding::inflate
) {

    override val viewModel: InsuranceContractCheckViewModel by viewModels()

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