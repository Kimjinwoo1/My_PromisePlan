package com.nepplus.my_promiseplan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.FragmentInvitedAppointmentsBinding

class InvitedAppointmentsFragment : BaseFragment() {

    lateinit var binding : FragmentInvitedAppointmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_invited_appointments,container,false)
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