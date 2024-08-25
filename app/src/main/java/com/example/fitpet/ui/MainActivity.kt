package com.example.fitpet.ui

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitpet.R
import com.example.fitpet.base.BaseActivity
import com.example.fitpet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainPageState, MainViewModel>(
    ActivityMainBinding::inflate
) {

    override val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
        initNavigation()
    }

    override fun initState() {
        launchWhenStarted {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as MainEvent)
                }
            }
        }
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun handleEvent(event: MainEvent) {
        when (event) {
            else -> {}
        }
    }
}