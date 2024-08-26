package com.example.fitpet.ui

import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<MainPageState>() {

    override val uiState = MainPageState(
        temp = ""
    )
}