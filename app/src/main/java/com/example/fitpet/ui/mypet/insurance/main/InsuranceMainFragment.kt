package com.example.fitpet.ui.mypet.insurance.main

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceMainBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.MyPet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceMainFragment : BaseFragment<FragmentInsuranceMainBinding, InsuranceMainPageState, InsuranceMainViewModel>(
    FragmentInsuranceMainBinding::inflate
) {
    override val viewModel: InsuranceMainViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.updatePrice(106500)
        setMyPet()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceMainEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceMainEvent) {
        when(event) {
            InsuranceMainEvent.GoToConsult -> goToConsult()
            InsuranceMainEvent.OpenMyPetDialog -> openMyPetDialog()
            InsuranceMainEvent.GoToCheck -> goToCheck()
            InsuranceMainEvent.GoToCharge -> goToCharge()
        }
    }

    private fun goToConsult() {
        //kakao 채널 연결
    }

    private fun goToCheck() {
        //보상내역 확인 연결
    }

    private fun goToCharge() {
        //보험금 청구 연결
    }

    private fun openMyPetDialog() {
        //다이얼로그 띄우기
    }


    private fun setMyPet(){
        val pet = MyPet(PetType.DOG, "보리", 11, "시츄")
        viewModel.updatePetInfo(pet)
    }

}