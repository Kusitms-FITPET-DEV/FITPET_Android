package com.example.fitpet.ui.insurance.charge.document

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeDocumentBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import com.example.fitpet.ui.insurance.charge.check.InsuranceChargeCheckFragmentArgs
import com.example.fitpet.ui.insurance.charge.document.InsuranceChargeDocumentViewModel.Companion.DETAIL_PHOTO
import com.example.fitpet.ui.insurance.charge.document.InsuranceChargeDocumentViewModel.Companion.ETC_PHOTO
import com.example.fitpet.ui.insurance.charge.document.InsuranceChargeDocumentViewModel.Companion.RECEIPT_PHOTO
import com.example.fitpet.ui.insurance.charge.document.photo.AddPhotoBottomSheet
import com.example.fitpet.ui.model.InsuranceCharge
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
        }
    }

    private fun handleEvent(event: InsuranceChargeDocumentEvent) {
        when (event) {
            InsuranceChargeDocumentEvent.ClickAddReceiptBtn -> showAddPhotoBottomSheet(RECEIPT_PHOTO)
            InsuranceChargeDocumentEvent.ClickAddDetailBtn -> showAddPhotoBottomSheet(DETAIL_PHOTO)
            InsuranceChargeDocumentEvent.ClickAddEtcBtn -> showAddPhotoBottomSheet(ETC_PHOTO)
            InsuranceChargeDocumentEvent.GoToAccountPage -> goToAccountPage()
            InsuranceChargeDocumentEvent.ClickDeleteReceiptBtn -> viewModel.deletePhoto(RECEIPT_PHOTO)
            InsuranceChargeDocumentEvent.ClickDeleteDetailBtn -> viewModel.deletePhoto(DETAIL_PHOTO)
            InsuranceChargeDocumentEvent.ClickDeleteEtcBtn -> viewModel.deletePhoto(ETC_PHOTO)
        }
    }

    private fun showAddPhotoBottomSheet(photoType: String) {
        AddPhotoBottomSheet { photo, uri -> viewModel.getPhoto(photo, uri, photoType) }.show(
            parentFragmentManager,
            BOTTOM_SHEET
        )
    }

    private fun goToAccountPage() {
        val action =
            InsuranceChargeDocumentFragmentDirections.actionInsuranceChargeDocumentToAccount(setDocumentData())
        findNavController().navigate(action, NavOptions.Builder().build())
    }

    private fun setDocumentData(): InsuranceCharge {
        val arguments: InsuranceChargeDocumentFragmentArgs by navArgs()
        val causeData = arguments.insuranceArgument

        with (viewModel.uiState) {
            val documentData = InsuranceCharge(causeType = causeData.causeType, hospitalVisitDate = causeData.hospitalVisitDate, receiptUrl = receiptPhoto.value, medicalExpensesUrl = detailPhoto.value, etcUrl = etcPhoto.value)
            return documentData
        }
    }
}