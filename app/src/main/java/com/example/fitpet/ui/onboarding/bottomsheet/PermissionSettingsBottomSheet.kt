package com.example.fitpet.ui.onboarding.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.databinding.FragmentPermissionSettingsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PermissionSettingsBottomSheet(
    private val onClickBtnAgreement: () -> Unit
) : BottomSheetDialogFragment() {
    private val binding: FragmentPermissionSettingsBottomSheetBinding by lazy {
        FragmentPermissionSettingsBottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAgreement.setOnClickListener {
                onClickBtnAgreement()
                dismiss()
            }
        }
    }
}