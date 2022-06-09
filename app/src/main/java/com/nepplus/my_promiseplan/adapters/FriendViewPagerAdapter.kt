package com.nepplus.my_promiseplan.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nepplus.my_promiseplan.fragments.MyFriendsListFragment
import com.nepplus.my_promiseplan.fragments.RequestFriendsListFragment

class FriendViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MyFriendsListFragment()
            else -> RequestFriendsListFragment()
        }
    }
}