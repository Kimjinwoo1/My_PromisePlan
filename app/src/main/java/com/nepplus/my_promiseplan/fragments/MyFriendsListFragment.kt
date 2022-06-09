package com.nepplus.my_promiseplan.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.FragmentMyFriendsListBinding

class MyFriendsListFragment : BaseFragment() {

    lateinit var binding : FragmentMyFriendsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_friends_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setValues()
    }
    override fun setEvents() {

    }

    override fun setValues() {

    }

}