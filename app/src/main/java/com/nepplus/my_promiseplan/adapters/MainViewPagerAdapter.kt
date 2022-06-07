package com.nepplus.my_promiseplan.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nepplus.my_promiseplan.fragments.InvitedAppointmentsFragment
import com.nepplus.my_promiseplan.fragments.MyAppointmentsFragment
import com.nepplus.my_promiseplan.fragments.SettingsFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MyAppointmentsFragment()
            1 -> InvitedAppointmentsFragment()
            else -> SettingsFragment()
        }
    }
}