package com.example.fitpet.ui.splash

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.PageState
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, PageState.Default, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
            // 추후에는 자동 로그인 처리와 관련된 함수가 추가될 수 있습니다.
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as SplashEvent)
                }
            }
        }
    }

    private fun handleEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.GoToOnBoarding -> TODO()
        }
    }
}