package com.example.fitpet.ui.insurance

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitpet.PageState
import com.example.fitpet.R
import com.example.fitpet.base.BaseActivity
import com.example.fitpet.databinding.ActivityInsuranceTempBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsuranceTempActivity :
    BaseActivity<ActivityInsuranceTempBinding, PageState.Default, InsuranceTempViewModel>(
        ActivityInsuranceTempBinding::inflate
    ) {

    override val viewModel: InsuranceTempViewModel by viewModels()
    private lateinit var navController: NavController

    override fun initView() {
        binding.viewModel = viewModel
        initNavigation()
    }

    override fun initState() {}

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.insurance_fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
    }
}