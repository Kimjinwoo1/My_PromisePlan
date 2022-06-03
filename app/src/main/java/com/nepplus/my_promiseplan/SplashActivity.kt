package com.nepplus.my_promiseplan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nepplus.my_promiseplan.main.LoginActivity

class SplashActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent : Intent
            myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)

        },2500)
    }
}