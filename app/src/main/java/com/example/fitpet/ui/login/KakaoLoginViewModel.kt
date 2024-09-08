package com.example.fitpet.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.FitPetDataStore
import com.example.fitpet.data.repository.AuthRepository
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.request.LoginRequest
import com.example.fitpet.model.response.LoginResponse
import com.example.fitpet.model.response.PetResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoLoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val petsRepository: PetsRepository,
    private val dataStore: FitPetDataStore
) : BaseViewModel<PageState.Default>() {
    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun onClickBtnKakaoLogin() {
        emitEventFlow(KakaoLoginEvent.OnClickKakaoLogin)
    }

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            authRepository.kakaoLogin(request = request).collect {
                resultResponse(it, ::onSuccessKaKaoLogin)
            }
        }
    }

    private fun onSuccessKaKaoLogin(token: LoginResponse) {
        viewModelScope.launch {
            dataStore.saveAccessToken(token.accessToken)
            dataStore.saveRefreshToken(token.refreshToken)
            isPetExist()
        }
    }

    private fun isPetExist() {
        viewModelScope.launch {
            petsRepository.getPetAllMainInfo().collect {
                resultResponse(it, ::onSuccessGetAllPetList)
            }
        }
    }

    private fun onSuccessGetAllPetList(response: PetResponse) {
        if (response.petCount == 0) emitEventFlow(KakaoLoginEvent.GoToPetNameInput)
        else emitEventFlow(KakaoLoginEvent.GoToInsuranceMain)
    }
}