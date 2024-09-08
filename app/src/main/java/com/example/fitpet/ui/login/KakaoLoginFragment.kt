package com.example.fitpet.ui.login

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fitpet.PageState
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentKakaoLoginBinding
import com.example.fitpet.model.request.LoginRequest
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class KakaoLoginFragment : BaseFragment<FragmentKakaoLoginBinding, PageState.Default, KakaoLoginViewModel>(
    FragmentKakaoLoginBinding::inflate
) {
    override val viewModel: KakaoLoginViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as KakaoLoginEvent)
                }
            }
        }
    }

    private fun handleEvent(event: KakaoLoginEvent) {
        when (event) {
            KakaoLoginEvent.OnClickKakaoLogin -> signInKakao()
            KakaoLoginEvent.GoToPetNameInput -> goToPetNameInput()
            KakaoLoginEvent.GoToInsuranceMain -> goToInsuranceMain()
        }
    }

    private fun signInKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            signInKakaoApp()
        } else {
            signInKakaoEmail()
        }
    }

    private fun signInKakaoApp() {
        UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
            if (error != null) {
                Timber.tag("KaKao Login Error").e(error.stackTraceToString())
                return@loginWithKakaoTalk
            }
            Toast.makeText(requireContext(), "로그인 성공하였습니다.", Toast.LENGTH_SHORT).show()
            login(token)
        }
    }

    private fun signInKakaoEmail() {
        UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
            if (error != null) {
                Timber.tag("KaKao Login Error").e(error.stackTraceToString())
                return@loginWithKakaoAccount
            }

            Toast.makeText(requireContext(), "로그인 성공하였습니다.", Toast.LENGTH_SHORT).show()
            login(token)
        }
    }

    private fun login(token: OAuthToken?) {
        token?.let {
            val request = token.idToken?.let { it1 ->
                LoginRequest(
                    accessToken = token.accessToken,
                    accessTokenExpiresAt = token.accessTokenExpiresAt.toString(),
                    refreshToken = token.refreshToken,
                    refreshTokenExpiresAt = token.refreshTokenExpiresAt.toString(),
                    idToken = it1
                )
            }
            if (request != null) {
                viewModel.login(request)
                Timber.i(request.toString())
            }
        }
    }

    private fun goToPetNameInput() {
        val action = KakaoLoginFragmentDirections.actionToKakaoLoginToPetNameInput()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()
        findNavController().navigate(action, navOptions)
    }

    private fun goToInsuranceMain() {
        val action = KakaoLoginFragmentDirections.actionToKakaoLoginToMypet()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()
        findNavController().navigate(action, navOptions)
    }
}