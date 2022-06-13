package com.nepplus.my_promiseplan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MyAppointmentRecyclerViewAdapter
import com.nepplus.my_promiseplan.databinding.FragmentInvitedAppointmentsBinding
import com.nepplus.my_promiseplan.databinding.FragmentMyAppointmentsBinding
import com.nepplus.my_promiseplan.modles.AppointmentData
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.ui.EditAppointmentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MyAppointmentsFragment : BaseFragment() {

    lateinit var binding : FragmentMyAppointmentsBinding

    lateinit var mAppointmentAdapter : MyAppointmentRecyclerViewAdapter
    var mAppointmentsList = ArrayList<AppointmentData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_appointments, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()
        getMyAppointmentListFromSever()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mAppointmentAdapter = MyAppointmentRecyclerViewAdapter(mContext, mAppointmentsList,true)
        binding.myAppointmentRecyclerView.adapter = mAppointmentAdapter
        binding.myAppointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getMyAppointmentListFromSever (){
        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    val br = response.body()!!
                    mAppointmentsList.clear()
                    mAppointmentsList.addAll(br.data.invitedAppointments)

                    mAppointmentAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

}