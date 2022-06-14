package com.nepplus.my_promiseplan.settings

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityChangePasswordBinding
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.utils.ContextUtil
import com.nepplus.my_promiseplan.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePassword : BasicActivity() {

    lateinit var binding : ActivityChangePasswordBinding






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change_password)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.positivePwBtn.setOnClickListener {
            val inPutPw = binding.contentPwEdt.text.toString()
            val newInPutPw = binding.contentNewPwEdt.text.toString()
//            1. 현재 비밀번호 작성 여부 확인
            if (inPutPw.isBlank()) {
                Toast.makeText(mContext, "현재 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            2. 새 비밀번호 작성 여부 확인
            if (newInPutPw.isBlank()){
                Toast.makeText(mContext, "새로운 비밀번호를 입력해주세요. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            3. 현재 비밀번호와 새비밀번호의 일치 여부 확인
            if (inPutPw == newInPutPw){
                Toast.makeText(mContext, "새로운 비밀번호를 변경해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            4. API 호출 후 비밀번호 변경
            apiList.patchRequestPwChange(inPutPw, newInPutPw)
                .enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            ContextUtil.setLoginToken(mContext,br.data.token)
//                            유저가변경 되었을시..
//                            GlobalData.loginUser = br.data.user
                            Toast.makeText(mContext, "비밀 번호가 변경 되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                    }
                }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
        })
    }
}

    override fun setValues() {

    }
}