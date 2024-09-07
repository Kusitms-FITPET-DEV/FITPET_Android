package com.example.fitpet.ui.onboarding

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentOnBoardingBinding
import com.example.fitpet.ui.onboarding.adapter.OnBoardingAdapter
import com.example.fitpet.ui.onboarding.bottomsheet.PermissionSettingsBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingPageState, OnBoardingViewModel>(
    FragmentOnBoardingBinding::inflate
) {
    override val viewModel: OnBoardingViewModel by viewModels()

    private val onBoardingAdapter = OnBoardingAdapter()
    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val deniedPermissions = permissions.filterValues { !it }.keys

            if (deniedPermissions.isEmpty()) {
                goToKaKaoLogin()
            }
        }

    override fun initView() {
        binding.apply {
            vm = viewModel

            onBoardingViewPager.apply {
                adapter = onBoardingAdapter
                indicatorView.attachTo(onBoardingViewPager)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if(position == adapter?.itemCount?.minus(1)) {
                            viewModel.setIsLastPage(true)
                        } else {
                            viewModel.setIsLastPage(false)
                        }
                    }
                })
            }
//         viewPager를 설정하는 코드인데, 눈여겨 볼 부분은 onPageSelected 함수입니다. 용도에 대해 말씀드리자면 뷰페이저의 마지막 페이지가 되면 시작하기 버튼이 등장하고
//         indicator는 사라지게 됩니다. 이 기능을 위해서 뷰페이저의 현재 페이지값이 전체 아이템 개수에서 1을 뺀 값과 같으면 마지막 페이지에 도달한 것이기 때문에
//         OnBoardingPageState에 존재하는 isLastPage 값을 true로 설정하고 마지막 페이지가 아니라면 false로 설정합니다.
//         isLastPage값은 xml 파일에서 data binding을 활용하여 접근하고 이 값에 따라 버튼과 indicator의 visibility를 조정합니다.

            viewModel.initOnBoardingItem()
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as OnBoardingEvent)
                }
            }

            launch {    // onBoardingItemList 변경이 감지되면 어댑터에게 이를 알립니다.
                viewModel.uiState.onBoardingItemList.collect {
                    onBoardingAdapter.submitList(it)
                }
            }
        }
    }

    private fun handleEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.GoToPermissionSettings -> checkPermissions()
        }
    }

    private fun checkPermissions() {
        if (!arePermissionsGranted()) {
            goToKaKaoLogin()
        } else {
            showBottomSheet()
        }
    }

    private fun arePermissionsGranted(): Boolean {
        val requiredPermissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.READ_MEDIA_IMAGES
        )

        return requiredPermissions.all { permission ->
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun goToKaKaoLogin() {
        val action = OnBoardingFragmentDirections.actionOnboardingToKakaoLogin()
        findNavController().navigate(action)
    }

    private fun showBottomSheet() {
        val onClickBtnAgreement = {
            requestPermissions()
        }

        val bottomSheet = PermissionSettingsBottomSheet(onClickBtnAgreement)
        bottomSheet.show(parentFragmentManager, "")
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.READ_MEDIA_IMAGES
        )
        requestPermissionsLauncher.launch(permissions)
    }
}