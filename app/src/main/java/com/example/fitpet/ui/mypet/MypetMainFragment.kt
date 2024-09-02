package com.example.fitpet.ui.mypet

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentMypetMainBinding
import com.example.fitpet.ui.mypet.adapter.MypetMainVPA
import com.example.fitpet.ui.mypet.insurance.nopet.InsuranceNoPetEvent
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MypetMainFragment : BaseFragment<FragmentMypetMainBinding, MypetMainPageState, MypetMainViewModel>(
    FragmentMypetMainBinding::inflate
) {
    private var _mypetVPA: MypetMainVPA? = null
    private val mypetVPA get() = _mypetVPA
    private val navigator by lazy { findNavController() }

    override val viewModel: MypetMainViewModel by viewModels()

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
        }
    }

    private fun goToAddPet() {
        navigator.navigate(R.id.action_mypet_to_pet_name_input)
    }

    private fun initListVPAdapter() {
        _mypetVPA = MypetMainVPA(this)
        with(binding) {
            vpMypetMain.adapter = mypetVPA
            TabLayoutMediator(tabMypetMain, vpMypetMain) { tab, position ->
                tab.text = context?.getString(tabTitles[position])
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