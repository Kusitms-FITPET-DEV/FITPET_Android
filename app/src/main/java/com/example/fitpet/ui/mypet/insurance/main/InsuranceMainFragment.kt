package com.example.fitpet.ui.mypet.insurance.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceMainBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.insurance.petEdit.PetBottomSheetFragment
import com.example.fitpet.ui.mypet.insurance.petEdit.PetBottomSheetViewModel
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceMainFragment : BaseFragment<FragmentInsuranceMainBinding, InsuranceMainPageState, InsuranceMainViewModel>(
    FragmentInsuranceMainBinding::inflate
) {
    override val viewModel: InsuranceMainViewModel by viewModels()
    private val viewModelBottom: PetBottomSheetViewModel by viewModels()
    private val myPetMainViewModel: MypetMainViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        binding.apply {
            vm = viewModel
            mainViewModel = myPetMainViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        lifecycleScope.launch {
            delay(5000)
            if (isAdded && view != null) {  // view가 여전히 살아 있는지 확인
                binding.fabInsuranceKakaoBig.shrink()
            }
        }

        setMonth()
//        viewModel.loadPetData()
        //처음 방지용
        if(myPetMainViewModel.uiState.petId.value!=0) {
            viewModel.fetchPetInsuranceInfo(myPetMainViewModel.uiState.petId.value!!, "")
        }else viewModel.loadPetData()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceMainEvent)
                }
            }
            launch {
                viewModel.uiState.insuranceSuggestionResponse.collect {
                    if (it != null) {
                        myPetMainViewModel.updatePetName(it.name)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceMainEvent) {
        when(event) {
            InsuranceMainEvent.GoToConsult -> goToConsult()
            InsuranceMainEvent.OpenMyPetDialog -> openMyPetDialog()
//            InsuranceMainEvent.GoToContractCheck -> goToContractCheck()
            InsuranceMainEvent.GoToCompensationCheck -> goToCompensationCheck()
            InsuranceMainEvent.GoToCharge -> goToCharge()
            InsuranceMainEvent.UpdatePetInfo -> setMyPet()
            InsuranceMainEvent.UpdateInsuranceInfo -> setInsuranceInfo()
        }
    }

    private fun goToConsult(){
        // 카카오톡 채널 추가하기 URL
        val url = TalkApiClient.instance.chatChannelUrl(CHANNEL_ID)

        // CustomTabs 로 열기
        KakaoCustomTabsClient.openWithDefault(requireContext(), url)
    }

    private fun goToCompensationCheck() {
        //보상내역 확인 연결
    }

    private fun goToContractCheck() {
        //보험계약 확인 연결
    }

    private fun goToCharge() {
        //보험금 청구 연결
    }

    private fun openMyPetDialog() {
        val dialogFragment = PetBottomSheetFragment()
        dialogFragment.show(childFragmentManager, "ListDialogueListselectFragment")
    }

    private fun fetchPet(){
        viewModel.updatePetInfo(viewModelBottom.uiState.clickedPet.value!!)
    }

    private fun clickedEditPet(){
        myPetMainViewModel.updatePetId(viewModelBottom.uiState.clickedPet.value?.petId!!)
    }


    private fun setMyPet(){
        viewModel.updatePetInfo(viewModel.uiState.petInfo.value!!)
        Timber.tag("log2").d(viewModel.getFirstPet()!!.toString())
        viewModel.fetchPetInsuranceInfo(viewModel.getFirstPet()!!, "")
        
        if(viewModel.uiState.petInfo.value?.species == "DOG"){
            Timber.d("dog~~")
            binding.ivNoRegisterMyPet.setImageResource(R.drawable.ic_mypet_dog)
        }else{
            binding.ivNoRegisterMyPet.setImageResource(R.drawable.ic_mypet_cat)
        }
        myPetMainViewModel.updatePetName(viewModel.uiState.petInfo.value?.name!!)
    }

    private fun setInsuranceInfo(){
        viewModel.updatePrice(viewModel.uiState.insuranceSuggestionResponse.value?.estimateList?.get(0)?.insuranceFee!!)
        viewModel.uiState.insuranceInfo.value?.let { viewModel.updateInsuranceInfo(it) }
        setInsuranceImg()

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.insuranceInfo.collect {
//                    viewModel.updateInsuranceInfo(it)
//                }
//                setInsuranceImg()
//            }
//        }
    }

    private fun setInsuranceImg(){
        var drawable: Int = 0
        val company = viewModel.uiState.insuranceInfo.value?.insuranceCompany

        when (company) {
            "DB손해보험" -> drawable = R.drawable.ic_db_v3
            "KB손해보험" -> drawable = R.drawable.ic_kb_v3
            "현대해상" -> drawable = R.drawable.ic_hyundai_v3
            "삼성화재" -> drawable = R.drawable.ic_samsung_v3
            "메리츠" -> drawable = R.drawable.ic_meritz_v3
            else -> drawable = R.drawable.ic_db_v3
        }
        viewModel.updateInsuranceImg(drawable)
        binding.ivInsuranceImg.setImageResource(drawable)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonth(){
        viewModel.setMonth()
    }

    companion object{
        const val CHANNEL_ID = "_cxdAfG"
    }

}