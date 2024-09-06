package com.example.fitpet.ui.insurance.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class InsuranceContractCheckAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentList = ArrayList<Pair<Fragment, Bundle?>>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        val (fragment, bundle) = fragmentList[position]
        if (bundle != null) {
            fragment.arguments = bundle
        }
        return fragment
    }

    fun addFragment(fragment: Fragment, bundle: Bundle? = null) {
        fragmentList.add(Pair(fragment, bundle))
        notifyItemInserted(fragmentList.size - 1)
    }
}