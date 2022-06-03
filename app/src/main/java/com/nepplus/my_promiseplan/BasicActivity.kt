package com.nepplus.my_promiseplan

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BasicActivity : AppCompatActivity(){

    lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun setupEvents()

    abstract fun setValues()

}