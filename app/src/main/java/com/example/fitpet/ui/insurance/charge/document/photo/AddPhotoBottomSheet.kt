package com.example.fitpet.ui.insurance.charge.document.photo

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fitpet.R
import com.example.fitpet.databinding.BottomSheetAddPhotoBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddPhotoBottomSheet(
    private val onSelectedPhoto: (String, File) -> Unit
): BottomSheetDialogFragment() {

    private val viewModel by viewModels<AddPhotoViewModel>()

    private var _binding: BottomSheetAddPhotoBinding? = null
    val binding: BottomSheetAddPhotoBinding
        get() = requireNotNull(_binding as BottomSheetAddPhotoBinding)

    private var photoUri: Uri? = null
    private val getTakePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if(it) {
            handlePhotoUriResult(photoUri)
    } }

    private val getGalleryImg = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imgUri = result.data?.data
            handlePhotoUriResult(imgUri)
        }
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
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as AddPhotoEvent)
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
        val photoFile = createImageFile()
        photoUri = getUriForFile(photoFile)
        getTakePhoto.launch(photoUri)
    }

    private fun showGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = getString(R.string.gallery_file_format)
        getGalleryImg.launch(intent)
    }

    private fun handlePhotoUriResult(uri: Uri?) {
        uri?.let { selectedUri ->
            val file = uri.toFile(requireContext())
            onSelectedPhoto(selectedUri.toString(), file)
            dismiss()
        }
    }

    private fun getUriForFile(file: File): Uri {
        val authority = getString(R.string.file_provider_format, requireContext().packageName)
        return FileProvider.getUriForFile(requireContext(), authority, file)
    }

    private fun createImageFile(): File {
        val now = SimpleDateFormat(getString(R.string.camera_photo_date_format), Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            getString(R.string.file_name_format, now),
            getString(R.string.jpg_format),
            storageDir
        )
    }

    fun Uri.toFile(context: Context): File {
        val contentResolver = context.contentResolver
        val fileName = getFileName(context) ?: "temp_file"
        val file = File(context.cacheDir, fileName)
        val inputStream: InputStream? = contentResolver.openInputStream(this)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file
    }

    private fun Uri.getFileName(context: Context): String? {
        var name: String? = null
        val cursor = context.contentResolver.query(this, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                name = it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
            }
        }
        return name
    }
}