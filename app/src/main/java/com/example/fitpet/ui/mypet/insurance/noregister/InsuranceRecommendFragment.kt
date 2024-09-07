package com.example.fitpet.ui.mypet.insurance.noregister

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceNoRegisterBinding
import com.example.fitpet.databinding.FragmentRecommendInsuranceBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.util.ResourceProvider
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
        }
    }

    private fun fetchInsurance(){
        with(binding){
            tvInsuranceName.text = viewModel.uiState.insuranceSuggestionResponse.value?.insuranceCompany
            tvInsuranceBoxMonthPrice.text = viewModel.getFormattedPrice(viewModel.uiState.insuranceSuggestionResponse.value?.afterDiscountFee!!)
            tvInsuranceBoxMonthPrice2.text = viewModel.getFormattedPrice(viewModel.uiState.insuranceSuggestionResponse.value?.afterDiscountFee!!)
            tvRangeNumberPercent.text = viewModel.uiState.insuranceSuggestionResponse.value?.discountList?.get(0)
            tvRangeNumberPercent2.text = viewModel.uiState.insuranceSuggestionResponse.value?.discountList?.get(1)
            tvGarenty1.text = viewModel.uiState.insuranceSuggestionResponse.value?.positiveList?.get(0)
            tvGarenty2.text = viewModel.uiState.insuranceSuggestionResponse.value?.positiveList?.get(1)
            tvGarenty3.text = viewModel.uiState.insuranceSuggestionResponse.value?.positiveList?.get(2)
            tvBig1.text = viewModel.uiState.insuranceSuggestionResponse.value?.dailyTreatLimit
            tvBig2.text = viewModel.uiState.insuranceSuggestionResponse.value?.dailySurgeryCostLimit
            tvBig3.text = viewModel.uiState.insuranceSuggestionResponse.value?.yearReward
            tvDetail1.text = viewModel.uiState.insuranceSuggestionResponse.value?.hipJointLimit
            tvDetail2.text = viewModel.uiState.insuranceSuggestionResponse.value?.skinDisease
            tvDetail3.text = viewModel.uiState.insuranceSuggestionResponse.value?.skinEtc
            tvDetail4.text = viewModel.uiState.insuranceSuggestionResponse.value?.oralCoverage
            tvDetail5.text = viewModel.uiState.insuranceSuggestionResponse.value?.dentalCoverage
            tvDetail6.text = viewModel.uiState.insuranceSuggestionResponse.value?.inspectionCoverage
            tvDetail7.text = viewModel.uiState.insuranceSuggestionResponse.value?.foreignObjectRemoval
            tvDetail8.text = viewModel.uiState.insuranceSuggestionResponse.value?.selfBurden
            tvDetail9.text = viewModel.uiState.insuranceSuggestionResponse.value?.compensationLiability
            tvDetail10.text = viewModel.uiState.insuranceSuggestionResponse.value?.funeralAid

        }
        setInsuranceImg()
    }

    private fun setInsuranceImg(){
        var drawable: Int = 0
        val company = company
        when (company) {
            "DB손해보험" -> drawable = R.drawable.ic_db_v3
            "KB손해보험" -> drawable = R.drawable.ic_kb_v3
            "현대해상" -> drawable = R.drawable.ic_hyundai_v3
            "삼성화재" -> drawable = R.drawable.ic_samsung_v3
            "메리츠" -> drawable = R.drawable.ic_meritz_v3
            else -> drawable = R.drawable.ic_db_v3
        }
        binding.ivCompany.setImageResource(drawable)
    }

    @Inject
    lateinit var resourceProvider: ResourceProvider

}