package com.example.fitpet.ui.mypet.life.hospitalRecord

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
import com.example.fitpet.databinding.FragmentHospitalRecordBinding
import com.example.fitpet.ui.mypet.life.hospitalRecord.adapter.HospitalRecordAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HospitalRecordFragment : BaseFragment<FragmentHospitalRecordBinding, HospitalRecordPageState, HospitalRecordViewModel>(
    FragmentHospitalRecordBinding::inflate
) {
    override val viewModel: HospitalRecordViewModel by viewModels()

    private val hospitalRecordAdapter = HospitalRecordAdapter()

    override fun initView() {
        binding.apply {
            vm = viewModel

            hospitalRecordRecyclerView.adapter = hospitalRecordAdapter
            hospitalRecordRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            topBar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.uiState.recordList.collect {
                    hospitalRecordAdapter.submitList(it + it)
                }
            }
        }
    }
}