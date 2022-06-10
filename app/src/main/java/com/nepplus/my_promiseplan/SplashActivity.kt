package com.nepplus.my_promiseplan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.common.util.Utility
import com.nepplus.my_promiseplan.main.LoginActivity
import com.nepplus.my_promiseplan.main.MainActivity
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.utils.ContextUtil
import com.nepplus.my_promiseplan.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BasicActivity() {

    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        getKeyHash()
        apiList.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    val br = response.body()!!

                    isTokenOk = true
                    GlobalData.loginUser = br.data.user
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent : Intent

            Log.d("토큰",isTokenOk.toString())
            Log.d("CB",ContextUtil.getAutoLogin(mContext).toString())

            if (isTokenOk && ContextUtil.getAutoLogin(mContext)){
                Toast.makeText(mContext, "${GlobalData.loginUser!!.nickname}님 환영합니다.", Toast.LENGTH_SHORT).show()
                myIntent = Intent(mContext,MainActivity::class.java)
            }
            else{
                myIntent =Intent(mContext,LoginActivity::class.java)
            }
            startActivity(myIntent)
            finish()

        },2500)
    }
    fun getKeyHash(){
        var keyHash = Utility.getKeyHash(this)
        Log.d("키헤시",keyHash)
    }
}