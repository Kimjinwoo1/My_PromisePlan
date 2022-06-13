package com.nepplus.my_promiseplan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MyAppointmentRecyclerViewAdapter
import com.nepplus.my_promiseplan.databinding.FragmentMyAppointmentsBinding
import com.nepplus.my_promiseplan.modles.AppointmentData
import com.nepplus.my_promiseplan.modles.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MyAppointmentsFragment : BaseFragment() {

    lateinit var binding : FragmentMyAppointmentsBinding

    lateinit var mAppointmentAdapter : MyAppointmentRecyclerViewAdapter
    var mAppointmentsList = ArrayList<AppointmentData>()

    val mTotalAppointmentList = ArrayList<AppointmentData>()

    val sdf = SimpleDateFormat("M / dd")
    var mSelectedDate = Calendar.getInstance()

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
        binding.dateLayout.setOnClickListener {
            if (binding.calendarView.visibility == View.GONE){
                binding.calendarView.visibility = View.VISIBLE
//                arrow나타내는것
                binding.arrowImg.setImageResource(R.drawable.baseline_keyboard_arrow_up_black_24dp)
            }
            else{
                binding.calendarView.visibility = View.GONE
                binding.arrowImg.setImageResource(R.drawable.baseline_keyboard_arrow_down_black_24dp)
            }
        }
        binding.calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            mSelectedDate.set(i,i2,i3)
            binding.dateTxt.text = sdf.format(mSelectedDate.time)
            getMyAppointmentListFromSever()
        }

    }

    override fun setValues() {
        binding.calendarView.visibility = View.GONE

        binding.dateTxt.text = sdf.format(mSelectedDate.time)
        mAppointmentAdapter = MyAppointmentRecyclerViewAdapter(mContext, mAppointmentsList,true)
        binding.myAppointmentRecyclerView.adapter = mAppointmentAdapter
        binding.myAppointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getMyAppointmentListFromSever (){
        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                Log.d("d",response.toString())

                if (response.isSuccessful){
                    val br = response.body()!!
                    mAppointmentsList.clear()
                    mTotalAppointmentList.clear()
                    mTotalAppointmentList.addAll(br.data.appointments)
                    mTotalAppointmentList.addAll(br.data.invitedAppointments)
                    val today = sdf.format(mSelectedDate.time)
                    for (appointment in mTotalAppointmentList){
                        if (sdf.format(appointment.datetime)== today){
                            mAppointmentsList.add(appointment)
                        }
                    }
                    mAppointmentAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

}