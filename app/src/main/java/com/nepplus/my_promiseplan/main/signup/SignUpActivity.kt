package com.nepplus.my_promiseplan.main.signup

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivitySignUpBinding

class SignUpActivity : BasicActivity() {

    lateinit var binding : ActivitySignUpBinding

    var isEmailDupOk = false
    var isNickDupOk = false
    var isPwDupOk =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {
        binding.signUpBtn.setOnClickListener {
//            1. 이메일 중복체크
            if (!isEmailDupOk){
                Toast.makeText(mContext, "이메일 중복 체크를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isPwDupOk){
                Toast.makeText(mContext, "비밀번호 중복 체크를 확인해 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isNickDupOk){
                Toast.makeText(mContext, "닉네임 중복 체크를 확인해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                signUp()
            }
        }
        binding.emailDupBtn.setOnClickListener {  }
        binding.nickDupBtn.setOnClickListener {  }
    }

    override fun setValues() {

    }

    fun signUp(){

    }

    fun dupCheck(type : String, value : String){

    }
}