package com.nepplus.my_promiseplan.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityLoginBinding
import com.nepplus.my_promiseplan.main.signup.SignUpActivity
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.utils.ContextUtil
import com.nepplus.my_promiseplan.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BasicActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents(){
        binding.loginBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            apiList.postRequestLogin(inputEmail,inputPw).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!

                        ContextUtil.setLoginToken(mContext,br.data.token)
                        ContextUtil.setAutoLogin(mContext, binding.autoLoginCb.isChecked)
                        GlobalData.loginUser = br.data.user

                        Toast.makeText(mContext, "${GlobalData.loginUser!!.nickname}님을 환영합니다", Toast.LENGTH_SHORT).show()

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                    else{
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val code = jsonObj.getInt("code")
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })


        }
        binding.signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext,SignUpActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues(){

    }
}