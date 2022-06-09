package com.nepplus.my_promiseplan.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.FriendViewPagerAdapter
import com.nepplus.my_promiseplan.databinding.ActivityMyFriendsBinding

class MyFriendsActivity : BasicActivity() {

    lateinit var binding : ActivityMyFriendsBinding

    lateinit var mFriendsPagerAdapter : FriendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mFriendsPagerAdapter = FriendViewPagerAdapter(this)
        binding.friendListViewPager.adapter = mFriendsPagerAdapter

//      ViewPager2 + TabLayout의 결합 코드
        TabLayoutMediator(binding.tabLayout,binding.friendListViewPager){tab,position ->
            when(position){
                0 -> tab.text = "내친구 목록"
                else -> tab.text = "친구 추가 요청"
            }
        }.attach()
    }
}