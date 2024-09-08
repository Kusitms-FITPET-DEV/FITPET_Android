package com.example.fitpet.ui.registration.petBirthInput

import android.text.InputFilter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetBirthInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PetBirthInputFragment : BaseFragment<FragmentPetBirthInputBinding, PetBirthInputPageState, PetBirthInputViewModel>(
    FragmentPetBirthInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetBirthInputViewModel by viewModels()

    private var petName = ""
    private var petBreed = ""
    private var petDetailBreed = ""

    override fun initView() {
        binding.apply {
            vm = viewModel
            val args: PetBirthInputFragmentArgs by navArgs()
            petName = args.petName
            petBreed = args.petBreed
            petDetailBreed = args.petDetailBreed
//            birthInputEditText.filters = arrayOf(InputFilter.LengthFilter(4))
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetBirthInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetBirthInputEvent) {
        when(event) {
            PetBirthInputEvent.GoToContactInfoInput -> goToContactInfoInput()
            PetBirthInputEvent.ShowSkipDialog -> showSkipDialog()
            PetBirthInputEvent.IneligibleDueToAge -> showInvalidAgeDialog()
            PetBirthInputEvent.InvalidFutureBirthYear -> showFutureYearDialog()
        }
    }

    private fun goToContactInfoInput() {
        val petBirth = viewModel.uiState.birth.value
        val action = PetBirthInputFragmentDirections.actionPetBirthInputToContactInfoInput(petName = petName, petBreed = petBreed, petDetailBreed = petDetailBreed, petBirth = petBirth)
        findNavController().navigate(action)
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.stop_registration_title),
            contentText = requireContext().getString(R.string.stop_registration_content),
            iconResourceId = R.drawable.ic_dog_sad,
            skipVisibility = true
        )
    }

    private fun showInvalidAgeDialog() {
        dialog.showDialog(
            onSkipClicked = {},
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.invalid_birth_year_title),
            contentText = requireContext().getString(R.string.invalid_birth_year_due_to_age),
            iconResourceId = R.drawable.ic_cat_sad,
            skipVisibility = false
        )
    }

    private fun showFutureYearDialog() {
        dialog.showDialog(
            onSkipClicked = {},
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.invalid_birth_year_title),
            contentText = requireContext().getString(R.string.invalid_birth_year_future_year),
            iconResourceId = R.drawable.ic_cat_sad,
            skipVisibility = false
        )
    }

    private fun goToMyPetInsurance() {
        val action = PetBirthInputFragmentDirections.actionPetBirthInputToMyPet()
        findNavController().navigate(action)
    }
}