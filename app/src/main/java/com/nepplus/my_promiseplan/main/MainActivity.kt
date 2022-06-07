package com.nepplus.my_promiseplan.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MainViewPagerAdapter
import com.nepplus.my_promiseplan.databinding.ActivityMainBinding

class MainActivity : BasicActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var mPagerAdapter :MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mPagerAdapter = MainViewPagerAdapter(this)
        binding.mainViewPager.adapter = mPagerAdapter

        binding.mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.myAppointments -> binding.mainViewPager.currentItem = 0
                R.id.invitedAppointments -> binding.mainViewPager.currentItem = 1
                R.id.setting -> binding.mainViewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }

    }
}