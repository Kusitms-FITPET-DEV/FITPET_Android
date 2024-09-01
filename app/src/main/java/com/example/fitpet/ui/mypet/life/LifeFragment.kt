package com.example.fitpet.ui.mypet.life

import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentLifeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifeFragment : BaseFragment<FragmentLifeBinding, LifePageState, LifeViewModel>(
    FragmentLifeBinding::inflate
) {
    override val viewModel: LifeViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initState() {
        TODO("Not yet implemented")
    }

}