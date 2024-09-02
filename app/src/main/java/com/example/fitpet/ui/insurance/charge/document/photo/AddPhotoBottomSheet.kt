package com.example.fitpet.ui.insurance.charge.document.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.databinding.BottomSheetAddPhotoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddPhotoBottomSheet(): BottomSheetDialogFragment() {

    private var _binding: BottomSheetAddPhotoBinding? = null
    val binding: BottomSheetAddPhotoBinding
        get() = requireNotNull(_binding as BottomSheetAddPhotoBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddPhotoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}