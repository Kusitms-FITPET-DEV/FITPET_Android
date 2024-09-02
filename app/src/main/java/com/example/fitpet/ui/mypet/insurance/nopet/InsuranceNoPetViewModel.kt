package com.example.fitpet.ui.mypet.insurance.nopet

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.ui.onboarding.OnBoardingEvent
import com.example.fitpet.ui.registration.petBreed.PetBreedEvent
import com.example.fitpet.ui.registration.petBreed.PetBreedPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InsuranceNoPetViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<InsuranceNoPetPageState>() {

    override val uiState: InsuranceNoPetPageState = InsuranceNoPetPageState()


    fun onAddPetClicked() {
        emitEventFlow(InsuranceNoPetEvent.GoToAddPet)
    }
}