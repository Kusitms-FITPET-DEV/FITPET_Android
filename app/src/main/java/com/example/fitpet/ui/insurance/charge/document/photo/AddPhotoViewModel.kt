package com.example.fitpet.ui.insurance.charge.document.photo

import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPhotoViewModel @Inject constructor(): BaseViewModel<PageState.Default>() {

    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun onClickCameraBtn() {
        emitEventFlow(AddPhotoEvent.ClickCamera)
    }
}