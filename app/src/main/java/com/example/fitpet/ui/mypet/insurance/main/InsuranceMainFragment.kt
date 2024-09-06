package com.example.fitpet.ui.mypet.insurance.main

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsuranceMainFragment : BaseFragment<FragmentInsuranceBinding, InsuranceMainPageState, InsuranceMainViewModel>(
    FragmentInsuranceBinding::inflate
) {
    override val viewModel: InsuranceMainViewModel by viewModels()

    override fun initView() {

    }

    override fun initState() {

    }

}