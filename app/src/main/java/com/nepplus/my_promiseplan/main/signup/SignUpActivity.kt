package com.nepplus.my_promiseplan.main.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ActivitySignUpBinding

class SignUpActivity : BasicActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}