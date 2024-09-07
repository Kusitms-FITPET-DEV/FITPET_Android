package com.example.fitpet.ui.mypet.life

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentLifeBinding
import com.example.fitpet.ui.mypet.life.adapter.AdvertisementAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifeFragment : BaseFragment<FragmentLifeBinding, LifePageState, LifeViewModel>(
    FragmentLifeBinding::inflate
) {
    override val viewModel: LifeViewModel by viewModels()

    private val adapter by lazy {
        AdvertisementAdapter(object: AdvertisementAdapter.OnItemChange {
            override fun onItemChange(id: Long) {
                viewModel.updatePageCount(id.toInt())
            }
        })
    }

    override fun initView() {

    }

    override fun initState() {

    }

}