package com.nepplus.my_promiseplan.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityMyPlanBinding

class MyPlan : BasicActivity() {

    lateinit var binding : ActivityMyPlanBinding
    val mList = arrayListOf("운동","공부","놀기")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_plan)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.myPlanSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(mContext, mList[p2], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun setValues() {
        binding.myPlanSpinner.adapter = ArrayAdapter<String>(mContext,
            androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,mList)
    }
}