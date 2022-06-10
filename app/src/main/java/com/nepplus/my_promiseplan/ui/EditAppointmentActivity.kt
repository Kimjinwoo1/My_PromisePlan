package com.nepplus.my_promiseplan.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BasicActivity() {

    lateinit var binding : ActivityEditAppointmentBinding

//      선택할 약속 일시를 저장할 맴버변수
    val mSelectedDateTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        날짜 선택
        binding.dataTxt.setOnClickListener {
//            날짜를 선택하고 할 일(인터페이스)를 작성
            val dl = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    mSelectedDateTime.set(year,month,day)

                    val sdf = SimpleDateFormat("yyyy. M. d (E)")
                    Log.d("선택된 시간",sdf.format(mSelectedDateTime.time))

                    binding.dataTxt.text = sdf.format(mSelectedDateTime.time)
                }
            }
//            DataPickerdialog 팝업
            val dpd = DatePickerDialog(
                mContext,dl,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
        binding.addBtn.setOnClickListener {
            val inputTitle = binding.titleEdt.toString()
//            약속제목 정하기
            if (inputTitle.isBlank()){
                Toast.makeText(mContext, "약속을 정해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            시간 선택
            binding.timeTxt.setOnClickListener {
                val tsl = object : TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
                        mSelectedDateTime.set(Calendar.HOUR_OF_DAY,hour)
                        mSelectedDateTime.set(Calendar.MINUTE, minute)

                        val sdf = SimpleDateFormat("a h:mm")
                        binding.timeTxt.text = sdf.format(mSelectedDateTime.time)
                    }
                }

                TimePickerDialog(
                    mContext,tsl,
                    mSelectedDateTime.get(Calendar.HOUR_OF_DAY),
                    mSelectedDateTime.get(Calendar.MINUTE),
                    false
                ).show()

            }

//            약속명 정하기
            val inputPlaceName = binding.placeNameTxt.text.toString()
            if (inputPlaceName.isBlank()){
                Toast.makeText(mContext, "약속 장소명을 정해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

    }

    override fun setValues() {
        titleTxt.text = "새 약속 만들기"

    }
}