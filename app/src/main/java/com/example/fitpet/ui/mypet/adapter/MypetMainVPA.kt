package com.example.fitpet.ui.mypet.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitpet.ui.mypet.insurance.main.InsuranceMainFragment
import com.example.fitpet.ui.mypet.insurance.nopet.InsuranceNoPetFragment
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterFragment
import com.example.fitpet.ui.mypet.life.LifeFragment

class MypetMainVPA(
    fragment: Fragment,
    private val isNoPet: Boolean,
    private val isNoRegister: Boolean
) : FragmentStateAdapter(fragment) {

    companion object {
        private const val INSURANCE_FRAGMENT_POSITION = 0
        private const val LIFE_FRAGMENT_POSITION = 1
        private const val TOTAL_COUNT = 2
    }

    override fun getItemCount(): Int = TOTAL_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            INSURANCE_FRAGMENT_POSITION -> {
                if (isNoPet) {
                    InsuranceNoPetFragment() // Pet이 없는 경우
                } else if (isNoRegister) {
                    InsuranceNoRegisterFragment() // 보험 등록이 없는 경우
                } else {
                    InsuranceMainFragment() // 일반 보험 메인 Fragment
                }
            }
            LIFE_FRAGMENT_POSITION -> LifeFragment() // Life Fragment
            else -> throw IllegalStateException("Invalid position")
        }
    }
}
