package com.example.fitpet.ui.splash

import androidx.lifecycle.ViewModel
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

): BaseViewModel<PageState.Default>(
    initialState = PageState.Default
) {

}