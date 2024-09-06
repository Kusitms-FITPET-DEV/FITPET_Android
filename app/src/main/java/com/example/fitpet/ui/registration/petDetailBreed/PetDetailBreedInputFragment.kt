package com.example.fitpet.ui.registration.petDetailBreed

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentPetDetailBreedInputBinding
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private var petBreed = ""

    override fun initView() {
        binding.apply {
            vm = viewModel
            val args: PetDetailBreedInputFragmentArgs by navArgs()
            petName = args.petName
            petBreed = args.petBreed

            binding.detailBreedAutoCompleteTextView.setOnDismissListener {
                Timber.e("드롭다운이 닫혔습니다.")
            }

            breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, mutableListOf())

            detailBreedAutoCompleteTextView.setAdapter(breedAdapter)
            detailBreedAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                val selectedBreed = breedAdapter.getItem(position)
                selectedBreed?.let {
                    viewModel.onBreedSelected(it)
                }
            }
            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            binding.detailBreedAutoCompleteTextView.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
                    showDropdown()
                }
            }
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.uiState.searchedBreedList.collect { breedList ->
                    Timber.e("업데이트 됐다 $breedList")
                    breedAdapter.clear()
                    breedAdapter.addAll(breedList)
                    breedAdapter.notifyDataSetChanged()
                    binding.detailBreedAutoCompleteTextView.requestFocus()
                    binding.detailBreedAutoCompleteTextView.showDropDown()
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
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToPetBirthInput(petName = petName, petBreed = petBreed, petDetailBreed = detailBreed)
        findNavController().navigate(action)
    }

    private fun showSkipDialog() {
        dialog.showDialog(
            onSkipClicked = { goToMyPetInsurance() },
            onResumeClicked = {}
        )
    }

    private fun goToMyPetInsurance() {
        val action = PetDetailBreedInputFragmentDirections.actionPetDetailBreedInputToMyPet()
        findNavController().navigate(action)
    }

    private fun showDropdown() {
        binding.detailBreedAutoCompleteTextView.apply {
            postDelayed({
                if (!hasFocus()) {
                    requestFocus()
                }
                showDropDown()
            }, 300)
        }
    }
}