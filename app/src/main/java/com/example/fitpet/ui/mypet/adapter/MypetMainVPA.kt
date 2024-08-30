package com.example.fitpet.ui.mypet.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitpet.ui.mypet.MypetMainFragment
import com.example.fitpet.ui.mypet.insurance.InsuranceFragment
import com.example.fitpet.ui.mypet.life.LifeFragment

class MypetMainVPA(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> InsuranceFragment()
            1 -> LifeFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}