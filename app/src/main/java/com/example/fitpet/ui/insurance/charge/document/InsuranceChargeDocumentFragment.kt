package com.example.fitpet.ui.insurance.charge.document

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeDocumentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceChargeDocumentFragment :
    BaseFragment<FragmentInsuranceChargeDocumentBinding, PageState.Default, InsuranceChargeDocumentViewModel>(
        FragmentInsuranceChargeDocumentBinding::inflate
    ) {

    override val viewModel: InsuranceChargeDocumentViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeDocumentEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeDocumentEvent) {
        when (event) {
            InsuranceChargeDocumentEvent.ClickAddReceiptBtn -> showAddPhotoBottomSheet()
        }
    }

    private fun showAddPhotoBottomSheet() {
        Timber.d("[보험금 청구] 영수증 추가 클릭")
    }
}