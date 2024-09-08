package com.example.fitpet.ui.mypet.life

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentLifeBinding
import com.example.fitpet.ui.mypet.MypetMainFragmentDirections
import com.example.fitpet.ui.mypet.life.adapter.AdvertisementAdapter
import com.example.fitpet.ui.mypet.life.adapter.InsuranceRecordAdapter
import com.example.fitpet.ui.onboarding.dialog.SkipDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LifeFragment : BaseFragment<FragmentLifeBinding, LifePageState, LifeViewModel>(
    FragmentLifeBinding::inflate
) {
    @Inject
    lateinit var dialog: SkipDialog

    override val viewModel: LifeViewModel by viewModels()

    private val advertisementAdapter = AdvertisementAdapter()
    private val insuranceRecordAdapter = InsuranceRecordAdapter()

    override fun initView() {
        binding.apply {
            vm = viewModel
            advertisementViewPager.adapter = advertisementAdapter
            myPetHospitalRecordViewPager.adapter = insuranceRecordAdapter
            myPetHospitalRecordViewPager.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            advertisementViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    viewModel.updatePageCount(position + 1)
                }
            })
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as LifeEvent)
                }
            }

            launch {
                viewModel.uiState.advertisement.collect { advertisements ->
                    advertisementAdapter.submitList(advertisements)
                }
            }

            launch {
                viewModel.uiState.pageData.collect { data ->
                    insuranceRecordAdapter.submitList(data.journalList + data.journalList)
                }
            }
        }
    }

    private fun handleEvent(event: LifeEvent) {
        when(event) {
            LifeEvent.GetNewQuote -> TODO()
            LifeEvent.ShowCorrectDialog -> showCorrectDialog()
            LifeEvent.ShowFalseDialog -> showFalseDialog()
            LifeEvent.GoToHospitalRecordDetail -> goToHospitalRecordDetail()
        }
    }

    private fun showCorrectDialog() {
        dialog.showDialog(
            onSkipClicked = {},
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.correct),
            contentText = viewModel.uiState.pageData.value.quiz.description,
            iconResourceId = R.drawable.ic_correct,
            skipVisibility = false
        )
    }

    private fun showFalseDialog() {
        dialog.showDialog(
            onSkipClicked = {},
            onResumeClicked = {},
            titleText = requireContext().getString(R.string.incorrect),
            contentText = viewModel.uiState.pageData.value.quiz.description,
            iconResourceId = R.drawable.ic_false,
            skipVisibility = false,
            buttonText = requireContext().getString(R.string.close)
        )
    }

    private fun goToHospitalRecordDetail() {
        val action = MypetMainFragmentDirections.actionMypetToHospitalRecord()
        findNavController().navigate(action)
    }
}