package com.example.fitpet.ui.mypet

import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentMypetMainBinding
import com.example.fitpet.databinding.FragmentMypetMainDrawerBinding
import com.example.fitpet.model.InsuranceAlarm
import com.example.fitpet.ui.model.InsuranceCharge
import com.example.fitpet.ui.mypet.adapter.MainInsuranceAlarmAdapter
import com.example.fitpet.ui.mypet.adapter.MypetMainVPA
import com.example.fitpet.util.ResourceProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MypetMainFragment : BaseFragment<FragmentMypetMainDrawerBinding, MypetMainPageState, MypetMainViewModel>(
    FragmentMypetMainDrawerBinding::inflate
) {
    private var _mypetVPA: MypetMainVPA? = null
    private val mypetVPA get() = _mypetVPA
    private val navigator by lazy { findNavController() }

    private var isFetched : Boolean = false

    override val viewModel: MypetMainViewModel by activityViewModels()

    private var _insuranceAlarmAdapter: MainInsuranceAlarmAdapter? = null
    private val insuranceAlarmAdapter
        get() = requireNotNull(_insuranceAlarmAdapter)

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() {
        // 초기 상태에 맞게 어댑터를 설정
        initListVPAdapter(isNoPet = false, isNoRegister = false)
        viewModel.setInsuranceAlarmList()
        binding.vpMypetMain.isUserInputEnabled = false
        viewModel.loadPetData()

        setAlarmDrawable()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as MypetMainEvent)
                }
            }
            launch {
                viewModel.uiState.petId.collect { petId ->
                    if (petId != null) {
                        viewModel.fetchPetInsuranceInfo(petId, "")
                    }
                }
            }
            launch {
                viewModel.uiState.insuranceAlarmList.collect { alarmList ->
                    Timber.d("[테스트] 클릭 후 -> $alarmList")
                    insuranceAlarmAdapter.submitList(alarmList)
                }
            }
        }
    }

    private fun handleEvent(event: MypetMainEvent) {
        when (event) {
            is MypetMainEvent.GoToAddPetButtonClick -> goToAddPet()
            MypetMainEvent.GoToMain -> goToMain()
            MypetMainEvent.GoToNoPet -> goToNoPet()
            MypetMainEvent.GoToNoRegister -> goToNoRegister()
            MypetMainEvent.FetchPetData -> fetchPetData()
            MypetMainEvent.FetchInsuranceData -> fetchInsuranceData()
            is MypetMainEvent.GoToInsuranceInfoCheckPage -> goToInsuranceInfo()
            is MypetMainEvent.GoTOCompensationPage -> goToCompensation()
            is MypetMainEvent.GoToInsuranceChargePage -> goToInsuranceCharge()
            MypetMainEvent.GoToRecommendButtonClick -> goToRecommend()
            MypetMainEvent.ClickedEditPet -> goToEditPage()
            MypetMainEvent.ClickedPet -> checkToGo()
            MypetMainEvent.ClickedEditPetMain -> goToEditMainPage()
        }
    }

    private fun checkToGo() {
        isFetched = false
        viewModel.fetchInsuranceData()
    }

    private fun goToEditPage() {
        val action = MypetMainFragmentDirections.actionMypetToPetEdit(viewModel.uiState.petId.value!!, true)
        navigator.navigate(action)
    }

    private fun goToEditMainPage() {
        val action = MypetMainFragmentDirections.actionMypetToPetEditMain()
        navigator.navigate(action)
    }


    private fun goToAddPet() {
        val action = MypetMainFragmentDirections.actionMypetToPetNameInput()
        navigator.navigate(action)
    }

    private fun fetchInsuranceData() {
        if(viewModel.checkInsurance() == 0) {
            goToMain() // 보험 정보가 있을 때
        } else {
            goToNoRegister() // 보험 정보가 없을 때
        }
    }

    private fun setAlarmDrawable() {
        _insuranceAlarmAdapter = MainInsuranceAlarmAdapter().apply {
            setOnClickListener(object : MainInsuranceAlarmAdapter.OnItemClickListener{
                override fun onItemClick(item: InsuranceAlarm) {
                    Timber.d("[클릭 테스트] -> ${item.historyId}")
                    item.historyId?.let { viewModel.changeAlarmConfirm(it) }
                }
            })
        }
        binding.rcvDrawerAlarm.adapter = insuranceAlarmAdapter

        binding.ivMypetNotification.setOnClickListener {
            binding.drawerlayoutMain.openDrawer(GravityCompat.END)
        }
        binding.ivDrawerClose.setOnClickListener {
            if (binding.drawerlayoutMain.isDrawerOpen(GravityCompat.END)) {
                binding.drawerlayoutMain.closeDrawer(GravityCompat.END)
            }
        }
    }

    private fun goToNoPet() {
        initListVPAdapter(isNoPet = true, isNoRegister = false)
    }

    private fun goToNoRegister() {
        initListVPAdapter(isNoPet = false, isNoRegister = true)
    }

    private fun goToMain() {
        initListVPAdapter(isNoPet = false, isNoRegister = false)
    }

    private fun goToInsuranceInfo() {
        // TODO 드롭다운 변경 이후 petId 로직 수정
        val petInfo = viewModel.uiState.petCount.value?.petList?.get(0)?.petId ?: 0
        val petId = viewModel.uiState.petId.value ?: 0
        val company = viewModel.uiState.company.value ?: ""
        val action = MypetMainFragmentDirections.actionMypetToInsuranceInfoCheck(petId, company)
        navigator.navigate(action)
    }

    private fun goToRecommend() {
        val action = MypetMainFragmentDirections.actionMypetToInsuranceRecommend(viewModel.uiState.petId.value!!,viewModel.uiState.priceId.value!!,viewModel.uiState.company.value.toString())
        navigator.navigate(action)
    }


    private fun fetchPetData() {
        if(!isFetched){
            if(viewModel.checkCount() > 0) {
                Timber.tag("log1").d(viewModel.getFirstPet()!!.toString())
                viewModel.fetchPetInsuranceInfo(viewModel.getFirstPet()!!, "")
                isFetched = true
            } else {
                isFetched = true
                goToNoPet() // Pet이 없을 때
            }
        }
    }

    private fun goToCompensation() {
        // TODO 드롭다운 변경 이후 petId 로직 수정
        val petInfo = viewModel.uiState.petCount.value?.petList?.get(0)?.petId ?: 0
        val petId = viewModel.uiState.petId.value ?: 0
        val action = MypetMainFragmentDirections.actionMypetToInsuranceCompensation(petId)
        navigator.navigate(action)
    }

    private fun goToInsuranceCharge() {
        // TODO 드롭다운 변경 이후 petId 로직 수정
        val petInfo = viewModel.uiState.petCount.value?.petList?.get(0)
        val petId = viewModel.uiState.petId.value ?: 0
        val petName = viewModel.uiState.petName.value ?: ""
        val insurancePetData = InsuranceCharge(petId = petId, targetName = petName)

        val action = MypetMainFragmentDirections.actionMypetToInsuranceChargeCause(insurancePetData)
        navigator.navigate(action)
    }

    private fun initListVPAdapter(isNoPet: Boolean, isNoRegister: Boolean) {
        _mypetVPA = MypetMainVPA(this, isNoPet, isNoRegister)
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
