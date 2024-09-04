package com.example.fitpet.ui.insurance.charge.contact.agree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fitpet.databinding.BottomSheetInsuranceAgreeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InsuranceAgreeBottomSheet(): BottomSheetDialogFragment() {

    private val viewModel by viewModels<InsuranceAgreeViewModel>()

    private var _binding: BottomSheetInsuranceAgreeBinding? = null
    val binding: BottomSheetInsuranceAgreeBinding
        get() = requireNotNull(_binding as BottomSheetInsuranceAgreeBinding)

    override fun onStart() {
        super.onStart()

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
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
    }
}