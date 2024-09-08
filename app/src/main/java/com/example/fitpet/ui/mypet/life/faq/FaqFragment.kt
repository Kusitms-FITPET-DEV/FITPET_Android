package com.example.fitpet.ui.mypet.life.faq

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentFaqBinding
import com.example.fitpet.ui.mypet.life.faq.adapter.FaqAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FaqFragment : BaseFragment<FragmentFaqBinding, FaqPageState, FaqViewModel>(
    FragmentFaqBinding::inflate
) {
    override val viewModel: FaqViewModel by viewModels()

    private val faqAdapter = FaqAdapter()

    override fun initView() {
        binding.apply {
            vm = viewModel

            faqRecyclerView.adapter = faqAdapter
            faqRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            topBar.btnBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.uiState.faqList.collect {
                    faqAdapter.submitList(it)
                }
            }
        }
    }
}