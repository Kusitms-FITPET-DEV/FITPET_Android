package com.example.fitpet.ui.insurance.info

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckBinding
import com.example.fitpet.ui.insurance.info.contract.InsuranceInfoContractFragment
import com.example.fitpet.ui.insurance.info.coverage.InsuranceInfoCoverageFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceContractCheckFragment: BaseFragment<FragmentInsuranceContractCheckBinding, InsuranceContractCheckPageState, InsuranceContractCheckViewModel>(
    FragmentInsuranceContractCheckBinding::inflate
) {

    override val viewModel: InsuranceContractCheckViewModel by viewModels()

    private val insurancePage = arrayListOf(getString(R.string.insurance_contract), getString(R.string.insurance_coverage))
    private var _insurancePageAdapter: InsuranceContractCheckAdapter? = null
    private val insurancePageAdapter
        get() = requireNotNull(_insurancePageAdapter)

    override fun initView() {
        binding.viewModel = viewModel

        initSetAdapter()
        viewModel.getInsuranceInfo()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                //
            }
        }
    }

    private fun initSetAdapter() {
        _insurancePageAdapter = InsuranceContractCheckAdapter(this@InsuranceContractCheckFragment)

        insurancePageAdapter.apply {
            addFragment(InsuranceInfoContractFragment())
            addFragment(InsuranceInfoCoverageFragment())
        }
        binding.viewPagerInsuranceContract.adapter = insurancePageAdapter

        initSetTabLayout()
    }

    private fun initSetTabLayout() {
        TabLayoutMediator(binding.tlInsuranceContract, binding.viewPagerInsuranceContract) {
            tab, position ->
            tab.text = insurancePage[position]
        }.attach()
    }
}