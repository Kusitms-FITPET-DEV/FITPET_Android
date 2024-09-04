package com.example.fitpet.ui.mypet.insurance

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsuranceFragment : BaseFragment<FragmentInsuranceBinding, InsurancePageState, InsuranceViewModel>(
    FragmentInsuranceBinding::inflate
) {
    override val viewModel: InsuranceViewModel by viewModels()

    override fun initView() {

    }

    override fun initState() {

    }

}