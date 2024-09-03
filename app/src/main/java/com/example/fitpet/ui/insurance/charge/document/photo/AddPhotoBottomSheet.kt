package com.example.fitpet.ui.insurance.charge.document.photo

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fitpet.databinding.BottomSheetAddPhotoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class AddPhotoBottomSheet(
    private val onSelectedPhoto: (String, Uri) -> Unit
): BottomSheetDialogFragment() {

    private val viewModel by viewModels<AddPhotoViewModel>()

    private var _binding: BottomSheetAddPhotoBinding? = null
    val binding: BottomSheetAddPhotoBinding
        get() = requireNotNull(_binding as BottomSheetAddPhotoBinding)

    private var photoUri: Uri? = null
    private val getTakePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if(it) {
            photoUri?.let { photoUri -> onSelectedPhoto(photoUri.toString(), photoUri) }
    } }

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
        binding.viewModel = viewModel

        initSettingEvent()
    }

    private fun initSettingEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.eventFlow.collect { event ->
                        handleEvent(event as AddPhotoEvent)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: AddPhotoEvent) {
        when (event) {
            AddPhotoEvent.ClickCamera -> showCamera()
        }
    }

    private fun showCamera() {
        photoUri = createImageFile()
        getTakePhoto.launch(photoUri)
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
    }
}