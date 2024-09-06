package com.example.fitpet.ui.insurance.charge.account

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeAccountBinding
import com.example.fitpet.ui.insurance.charge.account.InsuranceChargeAccountViewModel.Companion.BANK_SPINNER_DEFAULT
import com.example.fitpet.ui.model.InsuranceCharge
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
        binding.tbInsuranceCharge.btnTopBarInsuranceChargeBack.setOnClickListener { findNavController().popBackStack() }

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
            InsuranceChargeAccountEvent.GoToContactPage -> goToContactPage()
        }
    }

    private fun initSettingBankSpinner() {
        setSpinnerList()
        setSpinnerSelectedListener()
    }

    private fun setSpinnerList() {
        val bankList = resources.getStringArray(R.array.insurance_bank).toMutableList()
        bankList.add(BANK_SPINNER_DEFAULT, getString(R.string.insurance_charge_account))

        val adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, bankList) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view as TextView

                if (position == BANK_SPINNER_DEFAULT) {
                    textView.text = getString(R.string.insurance_charge_account)
                } else {
                    textView.text = bankList[position]
                }

                return view
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val dropDownView = super.getDropDownView(position, convertView, parent) as TextView
                if (position == BANK_SPINNER_DEFAULT) {
                    dropDownView.visibility = View.GONE
                } else {
                    dropDownView.visibility = View.VISIBLE
                }
                dropDownView.text = bankList[position]
                return dropDownView
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerInsuranceChargeAccountBank.adapter = adapter
    }

    private fun setSpinnerSelectedListener() {
        binding.spinnerInsuranceChargeAccountBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != BANK_SPINNER_DEFAULT) {
                    val selectedBank = parent?.getItemAtPosition(position).toString()
                    viewModel.isFirstSelection = false

                    Timber.d("[스피너] 선택 은행 -> $selectedBank")
                    viewModel.getAccountBank(selectedBank)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun goToContactPage() {
        val action = InsuranceChargeAccountFragmentDirections.actionInsuranceChargeAccountToContact(setAccountData())
        findNavController().navigate(action)
    }

    private fun setAccountData(): InsuranceCharge {
        val arguments: InsuranceChargeAccountFragmentArgs by navArgs()
        val beforeData = arguments.insuranceArgument

        with (viewModel.uiState) {
            val accountData = InsuranceCharge(causeType = beforeData.causeType, hospitalVisitDate = beforeData.hospitalVisitDate, receiptUrl = beforeData.receiptUrl, medicalExpensesUrl = beforeData.medicalExpensesUrl, etcUrl = beforeData.etcUrl, accountOwner = accountOwner.value, accountBank = accountBank.value, accountNumber = accountNumber.value)
            return accountData
        }
    }
}