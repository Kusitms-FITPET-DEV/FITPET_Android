package com.example.fitpet.ui.mypet.insurance.nopet

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceBinding
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputEvent
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputFragmentDirections
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceNoPetFragment : BaseFragment<FragmentInsuranceNoPetBinding, InsuranceNoPetPageState, InsuranceNoPetViewModel>(
    FragmentInsuranceNoPetBinding::inflate
) {
    private val mypetMainViewModel: MypetMainViewModel by viewModels({ requireParentFragment() })
    override val viewModel: InsuranceNoPetViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel

            Handler(Looper.getMainLooper()).postDelayed({
                binding.fabInsuranceKakaoBig.shrink()
            }, 5000)
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceNoPetEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceNoPetEvent) {
        when(event) {
            InsuranceNoPetEvent.GoToAddPet -> goToAddPet()
        }
    }

    private fun goToAddPet() {
        mypetMainViewModel.onInsuranceNoPetFragmentClick()
    }

}