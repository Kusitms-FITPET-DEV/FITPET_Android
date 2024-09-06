package com.example.fitpet.ui.mypet.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitpet.ui.mypet.MypetMainFragment
import com.example.fitpet.ui.mypet.insurance.InsuranceFragment
import com.example.fitpet.ui.mypet.insurance.nopet.InsuranceNoPetFragment
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterFragment
import com.example.fitpet.ui.mypet.life.LifeFragment

class MypetMainVPA(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private const val INSURANCE_FRAGMENT_POSITION = 0
        private const val LIFE_FRAGMENT_POSITION = 1
        private const val TOTAL_COUNT = 2
    }

    override fun getItemCount(): Int = TOTAL_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            INSURANCE_FRAGMENT_POSITION -> InsuranceNoRegisterFragment() // 후에 데이터에 따른 fragment 띄우기 구현 예정
            LIFE_FRAGMENT_POSITION -> LifeFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}