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

class InvitedAppointmentsFragment : BaseFragment() {

    lateinit var binding : FragmentInvitedAppointmentsBinding

    lateinit var mAppointAdapter : MyAppointmentRecyclerViewAdapter
    var mAppointmentList = ArrayList<AppointmentData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invited_appointments, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.addAppointmentBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        getMyAppointmentListFromServer()
    }

    override fun setValues() {
        mAppointAdapter = MyAppointmentRecyclerViewAdapter(mContext, mAppointmentList,false)
        binding.invitedAppointmentRecyclerView.adapter = mAppointAdapter
        binding.invitedAppointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getMyAppointmentListFromServer() {
        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mAppointmentList.clear()
                    mAppointmentList.addAll(br.data.appointments)

                    mAppointAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

}