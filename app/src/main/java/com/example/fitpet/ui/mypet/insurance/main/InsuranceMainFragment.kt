package com.example.fitpet.ui.mypet.insurance.main

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import kotlinx.coroutines.launch

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
        Handler(Looper.getMainLooper()).postDelayed({
            binding.fabInsuranceKakaoBig.shrink()
        }, 5000)
        setMonth()
        // 아래는 더미데이터 설정
        viewModel.updatePrice(106500)
        setMyPet()
        setInsuranceInfo()
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
        val pet = MyPet(PetType.DOG, "보리", 11, "시츄")
        viewModel.updatePetInfo(pet)
    }

    private fun setInsuranceInfo(){
        val insurance = InsuranceSuggestion(5, "meritz", "펫블리 반려견보험", 56602)
        viewModel.updateInsuranceInfo(insurance)
        setInsuranceImg()
    }

    private fun setInsuranceImg(){
        var drawable: Int = 0
        val company = viewModel.uiState.insuranceInfo.value.insuranceCompany
        when (company) {
            "db" -> drawable = R.drawable.ic_db_v3
            "kb" -> drawable = R.drawable.ic_kb_v3
            "hyundai" -> drawable = R.drawable.ic_hyundai_v3
            "samsung" -> drawable = R.drawable.ic_samsung_v3
            "meritz" -> drawable = R.drawable.ic_meritz_v3
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