package com.example.fitpet.ui.mypet

import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentMypetMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypetMainFragment : BaseFragment<FragmentMypetMainBinding, MypetMainPageState, MypetMainViewModel>(
    FragmentMypetMainBinding::inflate
) {
    override val viewModel: MypetMainViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initState() {
        TODO("Not yet implemented")
    }

}