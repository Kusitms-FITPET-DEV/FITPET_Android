package com.example.fitpet.ui.login

import androidx.lifecycle.ViewModel
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KakaoLoginViewModel @Inject constructor() : BaseViewModel<PageState.Default>() {
    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun onClickBtnKakaoLogin() {
        emitEventFlow(KakaoLoginEvent.OnClickKakaoLogin)
    }
}