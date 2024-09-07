package com.example.fitpet.ui.insurance.compensation

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceCompensationBinding
import com.example.fitpet.ui.insurance.compensation.adapter.InsuranceCompensationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceCompensationFragment :
    BaseFragment<FragmentInsuranceCompensationBinding, InsuranceCompensationPageState, InsuranceCompensationViewModel>(
        FragmentInsuranceCompensationBinding::inflate
    ) {

    override val viewModel: InsuranceCompensationViewModel by viewModels()
    val arguments: InsuranceCompensationFragmentArgs by navArgs()
    
    private var _compensationAdapter: InsuranceCompensationAdapter? = null
    private val compensationAdapter
        get() = requireNotNull(_compensationAdapter)

    override fun initView() {
        binding.viewModel = viewModel

        viewModel.getCompensationList(arguments.petId)
        initSetAdapter()
        initSetCompensationAdapterList()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceCompensationEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceCompensationEvent) {
        when (event) {
            InsuranceCompensationEvent.GoBackPage -> goBackPage()
        }
    }

    private fun initSetAdapter() {
        _compensationAdapter = InsuranceCompensationAdapter()
        binding.rcvInsuranceCompensation.adapter = compensationAdapter
    }

    private fun initSetCompensationAdapterList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.compensationItemList.collect { itemList ->
                    compensationAdapter.submitList(itemList)
                }
            }
        }
    }

    private fun goBackPage() {
        findNavController().popBackStack()
    }
}