package com.example.fitpet.ui.mypet

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentMypetMainBinding
import com.example.fitpet.ui.mypet.adapter.MypetMainVPA
import com.example.fitpet.ui.mypet.insurance.nopet.InsuranceNoPetEvent
import com.example.fitpet.util.ResourceProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MypetMainFragment : BaseFragment<FragmentMypetMainBinding, MypetMainPageState, MypetMainViewModel>(
    FragmentMypetMainBinding::inflate
) {
    private var _mypetVPA: MypetMainVPA? = null
    private val mypetVPA get() = _mypetVPA
    private val navigator by lazy { findNavController() }

    override val viewModel: MypetMainViewModel by activityViewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() {
        initListVPAdapter()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as MypetMainEvent)
                }
            }
        }
    }

    private fun handleEvent(event: MypetMainEvent) {
        when(event) {
            is MypetMainEvent.GoToAddPetButtonClick -> goToAddPet()
            is MypetMainEvent.GoToInsuranceInfoCheckPage -> goToInsuranceInfo()
            is MypetMainEvent.GoTOCompensationPage -> goToCompensation()
            is MypetMainEvent.GoToInsuranceChargePage -> goToInsuranceCharge()
            MypetMainEvent.GoToMain -> goToMain()
            MypetMainEvent.GoToNoPet -> goToNoPet()
            MypetMainEvent.GoToNoRegister -> goToNoRegister()
            MypetMainEvent.FetchPetData -> fetchPetData()
            MypetMainEvent.FetchInsuranceData -> fetchInsuranceData()
            MypetMainEvent.GoToRecommendButtonClick -> goToRecommend()
        }
    }

    private fun goToAddPet() {
        val action = MypetMainFragmentDirections.actionMypetToPetNameInput()
        navigator.navigate(action)
    }

    private fun goToInsuranceInfo() {
        val action = MypetMainFragmentDirections.actionMypetToInsuranceInfoCheck()
        navigator.navigate(action)

    private fun goToRecommend() {
        val action = MypetMainFragmentDirections.actionMypetToInsuranceRecommend(viewModel.uiState.petId.value!!,viewModel.uiState.priceId.value!!,viewModel.uiState.company.value.toString())
        navigator.navigate(action)
    }


    private fun fetchPetData() {
        if(!isFetched){
            if(viewModel.checkCount() > 0) {
                Timber.tag("log1").d(viewModel.getFirstPet()!!.toString())
                viewModel.fetchPetInsuranceInfo(viewModel.getFirstPet()!!, "70%")
                isFetched = true
            } else {
                isFetched = true
                goToNoPet() // Pet이 없을 때
            }
        }
    }

    private fun goToCompensation() {
        val action = MypetMainFragmentDirections.actionMypetToInsuranceCompensation()
        navigator.navigate(action)
    }

    private fun goToInsuranceCharge() {
        val action = MypetMainFragmentDirections.actionMypetToInsuranceChargeCause()
        navigator.navigate(action)
    }

    private fun initListVPAdapter() {
        _mypetVPA = MypetMainVPA(this)
        with(binding) {
            vpMypetMain.adapter = mypetVPA
            TabLayoutMediator(tabMypetMain, vpMypetMain) { tab, position ->
                tab.text = resourceProvider.getString(tabTitles[position])
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mypetVPA = null
    }

    companion object {
        private val tabTitles = listOf(R.string.tab_insurance, R.string.tab_life)
    }

}
