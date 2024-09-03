package com.example.fitpet.ui.insurance.charge.document.photo

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
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
import com.example.fitpet.R
import com.example.fitpet.databinding.BottomSheetAddPhotoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.io.File
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
            photoUri?.let {
                photoUri -> onSelectedPhoto(photoUri.toString(), photoUri)
                dismiss()
            }
    } }

    private val getGalleryImg = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imgUri = result.data?.data
            imgUri?.let {
                onSelectedPhoto(imgUri.toString(), imgUri)
                val imgFile = File(getRealPathFromURI(imgUri))
                dismiss()
            }
        }
    }

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
            AddPhotoEvent.ClickGallery -> showGallery()
        }
    }

    private fun showCamera() {
        photoUri = createImageFile()
        getTakePhoto.launch(photoUri)
    }

    private fun showGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, getString(R.string.gallery_file_format))
        getGalleryImg.launch(intent)
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat(getString(R.string.camera_photo_date_format)).format(Date())
        val fileName = String.format(getString(R.string.img_file_name), now)
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, getString(R.string.img_file_format))
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
    }

    private fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if (buildName.equals(getString(R.string.gallery_phone_build))) {
            return uri.path ?: ""
        }
        var columnIndex = 0
        var result = ""
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, proj, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            }
            result = cursor.getString(columnIndex)
            cursor.close()
            return result
        }

        return result
    }
}