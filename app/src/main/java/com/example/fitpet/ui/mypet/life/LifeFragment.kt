package com.example.fitpet.ui.mypet.life

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentLifeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifeFragment : BaseFragment<FragmentLifeBinding, LifePageState, LifeViewModel>(
    FragmentLifeBinding::inflate
) {
    override val viewModel: LifeViewModel by viewModels()

    override fun initView() {

    }

    override fun initState() {

    }

}