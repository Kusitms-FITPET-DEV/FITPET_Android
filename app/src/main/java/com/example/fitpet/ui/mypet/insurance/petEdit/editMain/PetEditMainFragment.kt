package com.example.fitpet.ui.mypet.insurance.petEdit.editMain

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentAddNewPetBinding
import com.example.fitpet.databinding.FragmentEditMyPetBinding
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.insurance.petEdit.PetBottomSheetEvent
import com.example.fitpet.ui.mypet.insurance.petEdit.adapter.MyAddPetRVA
import com.example.fitpet.ui.mypet.insurance.petEdit.adapter.MyPetRVA
import com.example.fitpet.ui.mypet.insurance.petEdit.dialog.DeleteDialog
import com.example.fitpet.ui.mypet.insurance.petEdit.dialog.NotSaveDialog
import com.example.fitpet.ui.mypet.insurance.petEdit.dialog.SaveDialog
import com.example.fitpet.ui.mypet.insurance.petEdit.edit.PetEditEvent
import com.example.fitpet.ui.mypet.insurance.petEdit.edit.PetEditPageState
import com.example.fitpet.ui.mypet.insurance.petEdit.edit.PetEditViewModel
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputEvent
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputFragmentDirections
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputViewModel
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PetEditMainFragment : BaseFragment<FragmentAddNewPetBinding, PetEditMainPageState, PetEditMainViewModel>(
    FragmentAddNewPetBinding::inflate
) {

    override val viewModel: PetEditMainViewModel by viewModels()

    private val navigator by lazy { findNavController() }

    override fun initView() {
        binding.apply {
            vm = viewModel
            binding.rvAddNewPet.adapter = myAddPetRVA
        }
        viewModel.loadPetData()
        binding.icBackRecommend.setOnClickListener { navigator.navigateUp() }
        binding.btnGoToAddMyPet.setOnClickListener{ navigator.navigate(PetEditMainFragmentDirections.actionPetEditMainToAdd())}
    }

    private val myAddPetRVA by lazy {
        MyAddPetRVA(
            onEditClicked = {mypet ->
                viewModel.updateEditPetId(mypet.petId)
                navigator.navigate(PetEditMainFragmentDirections.actionPetEditMainToEdit(mypet.petId, true))
            },
            onClicked = { mypet ->
                viewModel.updatePetInfo(mypet)
                navigator.navigate(PetEditMainFragmentDirections.actionPetEditMainToEdit(mypet.petId, false))
            }
        )
    }

    override fun initState(){
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetEditMainEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetEditMainEvent) {
        when(event) {
            PetEditMainEvent.FetchPetData -> fetchPetData()
        }
    }

    private fun fetchPetData() {
        myAddPetRVA.submitList(viewModel.uiState.petData.value?.petList)
    }


}