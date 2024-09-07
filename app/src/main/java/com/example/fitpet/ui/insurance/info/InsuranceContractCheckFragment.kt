package com.example.fitpet.ui.insurance.info

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceContractCheckBinding
import com.example.fitpet.ui.insurance.info.InsuranceContractCheckViewModel.Companion.CONTRACT
import com.example.fitpet.ui.insurance.info.InsuranceContractCheckViewModel.Companion.COVERAGE
import com.example.fitpet.ui.insurance.info.contract.InsuranceInfoContractFragment
import com.example.fitpet.ui.insurance.info.coverage.InsuranceInfoCoverageFragment
import com.example.fitpet.ui.model.InsuranceContractInfo
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceContractCheckFragment: BaseFragment<FragmentInsuranceContractCheckBinding, InsuranceContractCheckPageState, InsuranceContractCheckViewModel>(
    FragmentInsuranceContractCheckBinding::inflate
) {

    override val viewModel: InsuranceContractCheckViewModel by viewModels()

    private lateinit var insurancePage: ArrayList<String>
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
                viewModel.uiState.insuranceContractInfo.collect { info ->
                    initSetAdapterPage(info)
                }
            }
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceContractCheckEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceContractCheckEvent) {
        when (event) {
            InsuranceContractCheckEvent.GoBackPage -> goBackPage()
        }
    }

    private fun goBackPage() {
        findNavController().popBackStack()
    }

    private fun initSetAdapter() {
        _insurancePageAdapter = InsuranceContractCheckAdapter(this@InsuranceContractCheckFragment)
        insurancePage = arrayListOf(
            getString(R.string.insurance_contract),
            getString(R.string.insurance_coverage)
        )
    }

    private fun initSetAdapterPage(info: InsuranceContractInfo) {
        val contractData = Bundle().apply { putParcelable(CONTRACT, info.contract) }
        val coverageData = Bundle().apply { putParcelable(COVERAGE, info.coverage) }

        insurancePageAdapter.apply {
            addFragment(InsuranceInfoContractFragment().apply { arguments = contractData })
            addFragment(InsuranceInfoCoverageFragment().apply { arguments = coverageData })
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