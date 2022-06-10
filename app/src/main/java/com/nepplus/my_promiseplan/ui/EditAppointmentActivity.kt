package com.nepplus.my_promiseplan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityEditAppointmentBinding

class EditAppointmentActivity : BasicActivity() {

    lateinit var binding : ActivityEditAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.addBtn.setOnClickListener {
            val inputTitle = binding.titleEdt.toString()
//            약속제목 정하기
            if (inputTitle.isBlank()){
                Toast.makeText(mContext, "약속을 정해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            약속명 정하기
            val inputPlaceName = binding.placeNameTxt.text.toString()
            if (inputPlaceName.isBlank()){
                Toast.makeText(mContext, "약속 장소명을 정해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            날짜 선택

//            시간 선택

        }

    }

    override fun setValues() {
        titleTxt.text = "새 약속 만들기"

    }
}