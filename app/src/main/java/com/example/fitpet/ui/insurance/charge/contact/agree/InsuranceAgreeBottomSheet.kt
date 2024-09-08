package com.example.fitpet.ui.insurance.charge.contact.agree

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.example.fitpet.databinding.BottomSheetInsuranceAgreeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InsuranceAgreeBottomSheet(
    private val onClickAgreement: (Boolean) -> Unit
): BottomSheetDialogFragment() {

    private val viewModel by viewModels<InsuranceAgreeViewModel>()

    private var _binding: BottomSheetInsuranceAgreeBinding? = null
    val binding: BottomSheetInsuranceAgreeBinding
        get() = requireNotNull(_binding as BottomSheetInsuranceAgreeBinding)

    override fun onStart() {
        super.onStart()

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dlg = ( super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        } ) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetInsuranceAgreeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        clickAgreeBtn()
    }

    private fun clickAgreeBtn() {
        binding.btnInsuranceAgree.setOnClickListener {
            with (viewModel.uiState) {
                onClickAgreement(isSelectedChoice.value)
            }
            dismiss()
        }
    }
}