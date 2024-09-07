package com.example.fitpet.ui.mypet.insurance.noregister

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.databinding.FragmentInsuranceNoRegisterBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.adapter.InsuranceNoRegisterRVA
import com.example.fitpet.util.ResourceProvider
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class InsuranceNoRegisterFragment : BaseFragment<FragmentInsuranceNoRegisterBinding, InsuranceNoRegisterPageState, InsuranceNoRegisterViewModel>(
    FragmentInsuranceNoRegisterBinding::inflate
) {
    override val viewModel: InsuranceNoRegisterViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val insuranceNoRegisterRVA by lazy {
        InsuranceNoRegisterRVA(
            resourceProvider = resourceProvider,
            onClicked = { insurance ->
                //navigate
            }
        )
    }

    override fun initView() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycleScope.launch {
            delay(5000)
            binding?.let { binding ->
                if (isAdded) {
                    binding.fabInsuranceKakaoBig.shrink()
                }
            }
        }

        viewModel.loadPetData()
        initInsuranceNoRegisterRVAdapter()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceNoRegisterEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceNoRegisterEvent) {
        when(event) {
            InsuranceNoRegisterEvent.GoToConsult -> goToConsult()
            InsuranceNoRegisterEvent.ChangeRange -> changeRange()
            InsuranceNoRegisterEvent.OpenMyPetDialog -> openMyPetDialog()
            InsuranceNoRegisterEvent.ShowNothing -> showNothing()
            InsuranceNoRegisterEvent.FetchInsurance -> fetchInsurance()
            InsuranceNoRegisterEvent.FetchPetData -> fetchPetData()
        }
    }

    private fun fetchInsurance() {
        viewModel.updatePriceStart(viewModel.uiState.insuranceSuggestionResponse.value?.minInsuranceFee!!)
        viewModel.updatePriceEnd(viewModel.uiState.insuranceSuggestionResponse.value?.maxInsuranceFee!!)
        insuranceNoRegisterRVA.submitList(viewModel.uiState.insuranceSuggestion.value)
    }

    private fun fetchPetData() {
        Timber.tag("log122").d(viewModel.getFirstPet()!!.toString())
        viewModel.fetchPetInsuranceInfo(viewModel.getFirstPet()!!, viewModel.uiState.insuranceRangePercent.value.toString()+"%")
    }

    private fun changeRange() {
        // api 연결
        viewModel.fetchPetInsuranceInfo(viewModel.uiState.petInfo.value?.petId!!, viewModel.uiState.insuranceRangePercent.value.toString()+"%" )
        // 박스 안 보이게
        setUpBox(false)
    }

    private fun openMyPetDialog() {
        //다이얼로그 띄우기
    }

    private fun showNothing() {
        // 리사이클러 뷰 내용 없을 때 ui 보여주기
        viewModel.showNothingUI(true)
    }

    private fun setUpBox(bool: Boolean) {
        viewModel.updateBoxStatus(bool)
    }

    private fun initInsuranceNoRegisterRVAdapter() {
        binding.rvNoRegisterInsurance.adapter = insuranceNoRegisterRVA
    }

    private fun goToConsult(){
        // 카카오톡 채널 추가하기 URL
        val url = TalkApiClient.instance.chatChannelUrl(CHANNEL_ID)

        // CustomTabs 로 열기
        KakaoCustomTabsClient.openWithDefault(requireContext(), url)
    }

    companion object{
        const val CHANNEL_ID = "_cxdAfG"
    }

}