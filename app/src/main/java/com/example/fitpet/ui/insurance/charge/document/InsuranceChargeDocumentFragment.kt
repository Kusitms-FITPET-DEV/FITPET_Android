package com.example.fitpet.ui.insurance.charge.document

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeDocumentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsuranceChargeDocumentFragment: BaseFragment<FragmentInsuranceChargeDocumentBinding, PageState.Default, InsuranceChargeDocumentViewModel>(
    FragmentInsuranceChargeDocumentBinding::inflate
) {

    override val viewModel: InsuranceChargeDocumentViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        TODO("Not yet implemented")
    }
}