package com.example.fitpet.ui.mypet.insurance.nopet

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceNoPetBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputEvent
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputFragmentDirections
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputViewModel
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceNoPetFragment : BaseFragment<FragmentInsuranceNoPetBinding, InsuranceNoPetPageState, InsuranceNoPetViewModel>(
    FragmentInsuranceNoPetBinding::inflate
) {
    private val mypetMainViewModel: MypetMainViewModel by viewModels({ requireParentFragment() })
    override val viewModel: InsuranceNoPetViewModel by viewModels()

    override fun initView() {
        binding.apply {
            vm = viewModel

            lifecycleScope.launch {
                delay(5000)
                binding.let { binding ->
                    if (isAdded) {
                        binding.fabInsuranceKakaoBig.shrink()
                    }
                }
            }

        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceNoPetEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceNoPetEvent) {
        when(event) {
            InsuranceNoPetEvent.GoToAddPet -> goToAddPet()
            InsuranceNoPetEvent.GoToConsult -> goToConsult()
        }
    }

    private fun goToAddPet() {
        mypetMainViewModel.onInsuranceNoPetFragmentClick()
    }

    private fun goToConsult(){
        // 카카오톡 채널 추가하기 URL
        val url = TalkApiClient.instance.chatChannelUrl(CHANNEL_ID)

        // CustomTabs 로 열기
        KakaoCustomTabsClient.openWithDefault(requireContext(), url)
    }

    companion object{
        const val CHANNEL_ID = "_cxdAfG"
    }

}