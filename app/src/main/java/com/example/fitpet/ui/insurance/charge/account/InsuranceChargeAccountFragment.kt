package com.example.fitpet.ui.insurance.charge.account

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceChargeAccountFragment: BaseFragment<FragmentInsuranceChargeAccountBinding, InsuranceChargeAccountPageState, InsuranceChargeAccountViewModel>(
    FragmentInsuranceChargeAccountBinding::inflate
) {

    override val viewModel: InsuranceChargeAccountViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel

        initSettingBankSpinner()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeAccountEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeAccountEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun initSettingBankSpinner() {
        setSpinnerList()
        setSpinnerSelectedListener()
    }

    private fun setSpinnerList() {
        ArrayAdapter.createFromResource(requireContext(), R.array.insurance_bank, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerInsuranceChargeAccountBank.adapter = adapter
        }
    }

    private fun setSpinnerSelectedListener() {
        binding.spinnerInsuranceChargeAccountBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedBank = parent?.getItemAtPosition(position).toString()
                Timber.d("[테스트] 선택 은행 -> $selectedBank")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}