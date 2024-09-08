package com.example.fitpet.ui.registration.petDetailBreed

import androidx.fragment.app.viewModels
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetDetailBreedInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PetDetailBreedInputFragment : BaseFragment<FragmentPetDetailBreedInputBinding, PetDetailBreedInputPageState, PetDetailBreedInputViewModel>(
    FragmentPetDetailBreedInputBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: PetDetailBreedInputViewModel by viewModels()

    private lateinit var breedAdapter: ArrayAdapter<String>
    private var petName = ""

    override fun initView() {
        binding.apply {
            vm = viewModel
            val args: PetDetailBreedInputFragmentArgs by navArgs()
            petName = args.petName
            viewModel.setPetBreed(args.petBreed)

            breedAdapter = PetDetailBreedArrayAdapter(
                requireContext(),
                viewModel.uiState.searchedBreedList.value,
                viewModel.uiState.detailBreed.value
            )

            detailBreedAutoCompleteTextView.apply {
                setAdapter(breedAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    val selectedBreed = breedAdapter.getItem(position)
                    Timber.e("selected breed: $selectedBreed")
                    selectedBreed?.let {
                        viewModel.onBreedSelected(selectedBreed)
                    }
                    dismissDropDown()
                }
                setDropDownBackgroundResource(R.drawable.bg_rectangle_filled_bg_primary_radius_8)
                dropDownAnchor = root.id
            }
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.uiState.searchedBreedList.collect { breedList ->
                    Timber.e("업데이트 됐다 $breedList")
                    breedAdapter = PetDetailBreedArrayAdapter(
                        requireContext(),
                        viewModel.uiState.searchedBreedList.value,
                        viewModel.uiState.detailBreed.value
                    )
                    binding.detailBreedAutoCompleteTextView.showDropDown()
                    binding.detailBreedAutoCompleteTextView.setAdapter(breedAdapter)
                }
            }

            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetDetailBreedInputEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetDetailBreedInputEvent) {
        when(event) {
            PetDetailBreedInputEvent.GoToPetBirthInput -> goToPetBirthInput()
            PetDetailBreedInputEvent.ShowSkipDialog -> showSkipDialog()
            PetDetailBreedInputEvent.ShowDropdown -> showDropdown()
        }
    }

    private fun goToPetBirthInput() {
        val detailBreed = viewModel.uiState.selectedDetailBreed.value
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToPetBirthInput(petName = petName, petBreed = viewModel.uiState.selectedBreed.value, petDetailBreed = detailBreed)
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

    private fun goToMyPetInsurance() {
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToMyPet()
        findNavController().navigate(action)
    }

    private fun showDropdown() {
        binding.detailBreedAutoCompleteTextView.apply {
            postDelayed({
                showDropDown()
            }, 0)
        }
    }
}