package com.example.fitpet.ui.insurance.info

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class InsuranceContractCheckAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentList = ArrayList<Fragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size - 1)
    }
}