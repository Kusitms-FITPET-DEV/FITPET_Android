package com.example.fitpet.ui.splash

import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
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
            val videoUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.splash_video}")
            vm = viewModel
            splashVideoView.apply {
                setVideoURI(videoUri)
                setOnCompletionListener { viewModel.processSplash() }
                start()
            }
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
            SplashEvent.GoToOnBoarding -> goToOnBoarding()
        }
    }

    private fun goToOnBoarding() {
        val action = SplashFragmentDirections.actionSplashToOnboarding()
        findNavController().navigate(action, NavOptions.Builder()
            .setPopUpTo(R.id.fragmentSplash, true)
            .build())
    }
}