package com.example.fitpet.ui.mypet.insurance.petEdit.edit

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentEditMyPetBinding
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.ui.mypet.MypetMainViewModel
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
class PetEditFragment : BaseFragment<FragmentEditMyPetBinding, PetEditPageState, PetEditViewModel>(
    FragmentEditMyPetBinding::inflate
) {

    @Inject
    lateinit var saveDialog: SaveDialog
    @Inject
    lateinit var notSaveDialog: NotSaveDialog
    @Inject
    lateinit var deleteDialog: DeleteDialog

    override val viewModel: PetEditViewModel by viewModels()

    private val navigator by lazy { findNavController() }

    private val petId: Int? by lazy {
        arguments?.getInt("petId")
    }

    private val isEdit: Boolean? by lazy {
        arguments?.getBoolean("isEdit")
    }

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
        viewModel.fetchPetInsuranceInfo(petId!!, "")
        binding.icBackEdit.setOnClickListener { showNotSaveDialog() }
        binding.btnSave.setOnClickListener{ goToSave() }
        binding.tvEraseAll.setOnClickListener{showEraseDialog()}
        setYear()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetEditEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetEditEvent) {
        when(event) {
            PetEditEvent.GoToBack -> showNotSaveDialog()
            PetEditEvent.GoToSave -> goToSave()
            PetEditEvent.GoToDelete -> showEraseDialog()
            PetEditEvent.FetchPetData -> fetchPetData()
            PetEditEvent.Saved -> navigateToStart()
        }
    }

    private fun navigateToStart() {
        showSaveDialog()
    }

    private fun goToBack() {
        navigator.navigateUp()
    }
    private fun goToSave() {
        with(binding) {
            if(etName.text.isNotEmpty() && etYear.text.isNotEmpty() && etSpecies.text.isNotEmpty()) {
                if (isEdit == true) {
                    viewModel.editPet(
                        petId!!,
                        etName.text.toString(),
                        checkPetBreed(),
                        etSpecies.text.toString(),
                        etYear.text.toString()
                    )
                } else {
                    viewModel.registerPet(
                        etName.text.toString(),
                        checkPetBreed(),
                        etSpecies.text.toString(),
                        etYear.text.toString()
                    )
                }
                viewModel.updateFlow()
            }
        }
    }

    private fun checkPetBreed(): String{
        if (viewModel.uiState.breed.value == PetType.DOG) {
            return "DOG"
        } else return "CAT"
    }

    private fun goToDelete() {
        viewModel.deletePet(petId!!)
        navigator.navigate(PetEditFragmentDirections.actionPetEditToMainEdit())
    }

    private fun fetchPetData() {
        with(binding) {
            if (isEdit == true) {
                etName.hint = viewModel.uiState.insuranceSuggestionResponse.value?.name
                etSpecies.hint = viewModel.uiState.insuranceSuggestionResponse.value?.breed
                etYear.hint =
                    viewModel.uiState.insuranceSuggestionResponse.value?.birthYear.toString()
                if (viewModel.uiState.insuranceSuggestionResponse.value?.species == "DOG") {
                    viewModel.updateBreed("DOG")
                } else viewModel.updateBreed("CAT")
            }
        }
    }

    private fun setYear() {
        binding.etYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.length > 4) {
                        binding.etYear.setText(it.subSequence(0, 4))
                        binding.etYear.setSelection(4)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun showSaveDialog(){
        saveDialog.showDialog(
            onResumeClicked = {navigator.navigate(PetEditFragmentDirections.actionPetEditToMainEdit())},
            onSkipClicked = { },
            skipVisibility = false
        )
    }

    private fun showNotSaveDialog(){
        notSaveDialog.showDialog(
            onSkipClicked = { },
            onResumeClicked = {goToBack()},
            skipVisibility = true
        )
    }

    private fun showEraseDialog(){
        deleteDialog.showDialog(
            onSkipClicked = {goToDelete()},
            onResumeClicked = {},
            skipVisibility = true
        )
    }

}