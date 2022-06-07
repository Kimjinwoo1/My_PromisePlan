package com.nepplus.my_promiseplan

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nepplus.my_promiseplan.api.APIList
import com.nepplus.my_promiseplan.api.SeverApi
import retrofit2.Retrofit
import retrofit2.create

abstract class BasicActivity : AppCompatActivity(){

    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        retrofit = SeverApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()

    abstract fun setValues()

}