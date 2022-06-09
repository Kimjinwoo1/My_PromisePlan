package com.nepplus.my_promiseplan.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityMyFriendsBinding

class MyFriendsActivity : BasicActivity() {

    lateinit var binding : ActivityMyFriendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}