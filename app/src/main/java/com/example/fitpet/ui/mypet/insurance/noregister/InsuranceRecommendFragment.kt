package com.example.fitpet.ui.mypet.insurance.noregister

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceNoRegisterBinding
import com.example.fitpet.databinding.FragmentRecommendInsuranceBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterFragment.Companion.CHANNEL_ID
import com.example.fitpet.util.ResourceProvider
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class InsuranceRecommendFragment : BaseFragment<FragmentRecommendInsuranceBinding, InsuranceRecommendPageState, InsuranceRecommendViewModel>(
    FragmentRecommendInsuranceBinding::inflate
) {

    override val viewModel: InsuranceRecommendViewModel by viewModels()

    private val petId: Int? by lazy {
        arguments?.getInt("petId")
    }

    private val priceId: Int? by lazy {
        arguments?.getInt("priceId")
    }

    private val company: String? by lazy {
        arguments?.getString("company")
    }

    override fun initView() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.loadInsuranceData(petId!!, priceId!!)
        binding.icBackRecommend.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceRecommendEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceRecommendEvent) {
        when(event) {
            InsuranceRecommendEvent.FetchInsurance -> fetchInsurance()
            InsuranceRecommendEvent.GoToConsult -> goToConsult()
        }
    }

    private fun fetchInsurance(){
        with(binding){
            with(viewModel.uiState.insuranceSuggestionResponse){
            tvInsuranceName.text = value?.insuranceName
            tvInsuranceBoxMonthPrice.text =
                viewModel.getFormattedPrice(value?.afterDiscountFee!!)
            tvInsuranceBoxMonthPrice2.text =
                viewModel.getFormattedPrice(value?.beforeDiscountFee!!)
            tvRangeNumberPercent.text =
                value?.discountList?.get(0)
            tvRangeNumberPercent2.text =
                value?.discountList?.get(1)
            tvGarenty1.text =
                value?.positiveList?.get(0)
            tvGarenty2.text =
                value?.positiveList?.get(1)
            tvGarenty3.text =
                value?.positiveList?.get(2)
            tvBig1.text = value?.dailyTreatLimit
            tvBig2.text = value?.dailySurgeryCostLimit
            tvBig3.text = value?.yearReward
            tvDetail1.text = value?.hipJointLimit
            tvDetail2.text = value?.skinDisease
            tvDetail3.text = value?.skinEtc
            tvDetail4.text = value?.oralCoverage
            tvDetail5.text = value?.dentalCoverage
            tvDetail6.text = value?.inspectionCoverage
            tvDetail7.text =
                value?.foreignObjectRemoval
            tvDetail8.text = value?.selfBurden
            tvDetail9.text =
                value?.compensationLiability
            tvDetail10.text = value?.funeralAid
        }
        }
        setInsuranceImg()
    }

    private fun goToConsult(){
        // 카카오톡 채널 추가하기 URL
        val url = TalkApiClient.instance.chatChannelUrl(CHANNEL_ID)

        // CustomTabs 로 열기
        KakaoCustomTabsClient.openWithDefault(requireContext(), url)
    }

    private fun setInsuranceImg(){
        var drawable: Int = 0
        val company = company
        when (company) {
            "DB손해보험" -> drawable = R.drawable.ic_db_recommend
            "KB손해보험" -> drawable = R.drawable.ic_kb_recommend
            "현대해상" -> drawable = R.drawable.ic_hyundai_recommend
            "삼성화재" -> drawable = R.drawable.ic_samsung_recommend
            "메리츠" -> drawable = R.drawable.ic_meritz_recommend
            else -> drawable = R.drawable.ic_db_v3
        }
        binding.ivCompany.setImageResource(drawable)
    }

    @Inject
    lateinit var resourceProvider: ResourceProvider

}