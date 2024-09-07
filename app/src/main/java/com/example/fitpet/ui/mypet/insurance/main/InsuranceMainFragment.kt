package com.example.fitpet.ui.mypet.insurance.main

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceMainBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.ui.mypet.MypetMainViewModel
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
        viewModel.loadPetData()
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
        //다이얼로그 띄우기
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
    }

    private fun setInsuranceInfo(){
        viewModel.updatePrice(viewModel.uiState.insuranceSuggestionResponse.value?.estimateList?.get(0)?.insuranceFee!!)
        viewModel.updateInsuranceInfo(viewModel.uiState.insuranceInfo.value!!)
        setInsuranceImg()
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