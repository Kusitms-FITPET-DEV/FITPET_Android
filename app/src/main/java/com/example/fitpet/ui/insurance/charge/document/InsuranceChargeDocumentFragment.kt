package com.example.fitpet.ui.insurance.charge.document

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeDocumentBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import com.example.fitpet.ui.insurance.charge.document.photo.AddPhotoBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeDocumentFragment :
    BaseFragment<FragmentInsuranceChargeDocumentBinding, InsuranceChargeDocumentPageState, InsuranceChargeDocumentViewModel>(
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
            launch {
                viewModel.uiState.selectedPhoto.collect { photo ->
                    binding.ivInsuranceChargeDocumentReceipt.setImageURI(photo.toUri())
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
        AddPhotoBottomSheet{ photo, uri -> viewModel.getSelectedPhoto(photo, uri)}.show(parentFragmentManager, BOTTOM_SHEET)
    }
}