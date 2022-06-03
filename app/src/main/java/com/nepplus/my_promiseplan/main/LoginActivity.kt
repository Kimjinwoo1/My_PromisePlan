package com.nepplus.my_promiseplan.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setupEvents()
        setValues()
    }

    fun setupEvents(){
        binding.loginBtn.setOnClickListener {

        }
        binding.signUpBtn.setOnClickListener {
            val myIntent = Intent()
        }

    }

    fun setValues(){

    }
}