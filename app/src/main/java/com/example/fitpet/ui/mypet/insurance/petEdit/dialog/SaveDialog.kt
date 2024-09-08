package com.example.fitpet.ui.mypet.insurance.petEdit.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.fitpet.R
import com.example.fitpet.databinding.DialogSaveBinding
import com.example.fitpet.databinding.DialogSkipBinding
import com.google.android.material.internal.ViewUtils.dpToPx
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

@SuppressLint("RestrictedApi")
class SaveDialog @Inject constructor(
    @ActivityContext context: Context
): AlertDialog(context) {
    private val binding: DialogSaveBinding by lazy {
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_save, null, false)
    }

    private val dialog: Dialog by lazy {
        Dialog(context).apply {
            setContentView(binding.root)
            setCancelable(true)

            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(window?.attributes)
                width = (context.resources.displayMetrics.widthPixels - dpToPx(context, 60)).toInt()
                height = WindowManager.LayoutParams.WRAP_CONTENT
            }

            window?.attributes = layoutParams
        }
    }

    fun showDialog(
        onResumeClicked: () -> Unit,
        skipVisibility: Boolean = true
    ) {
        binding.apply {

            singleBtnSpace.visibility = if (skipVisibility) View.GONE else View.VISIBLE

            btnResume.setOnClickListener {
                onResumeClicked()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun dismiss() {
        dialog.dismiss()
    }
}