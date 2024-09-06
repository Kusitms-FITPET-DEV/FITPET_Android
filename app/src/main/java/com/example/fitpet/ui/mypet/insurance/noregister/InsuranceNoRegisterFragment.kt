package com.example.fitpet.ui.mypet.insurance.noregister

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.databinding.FragmentInsuranceNoRegisterBinding
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.adapter.InsuranceNoRegisterRVA
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.AndroidEntryPoint
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
        initInsuranceNoRegisterRVAdapter()
        viewModel.updatePriceStart(56000)
        viewModel.updatePriceEnd(60000)
        setMyPet()
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
        }
    }

    private fun goToConsult() {
        //kakao 채널 연결
    }

    private fun changeRange() {
        // api 연결
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
        val list = listOf(
            InsuranceSuggestion(1, "db", "펫블리 반려견보험", 56602),
            InsuranceSuggestion(2, "kb", "펫블리 반려견보험", 56602),
            InsuranceSuggestion(3, "samsung", "펫블리 반려견보험", 56602),
            InsuranceSuggestion(4, "hyundai", "펫블리 반려견보험", 56602),
            InsuranceSuggestion(5, "meritz", "펫블리 반려견보험", 56602)
        )
        insuranceNoRegisterRVA.submitList(list)
    }


    fun setMyPet(){
        val pet = MyPet(PetType.DOG, "보리", 11, "시츄")
        viewModel.updatePetInfo(pet)
    }

}