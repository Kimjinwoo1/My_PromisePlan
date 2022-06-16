package com.nepplus.my_promiseplan

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nepplus.my_promiseplan.api.APIList
import com.nepplus.my_promiseplan.api.SeverApi
import retrofit2.Retrofit

abstract class BasicActivity : AppCompatActivity(){

    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    lateinit var titleTxt : TextView
    lateinit var addBtn : ImageView

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        supportActionBar?.let {
            setCustomActionBar()
        }

        retrofit = SeverApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar(){
        val defActionBar = supportActionBar!!

        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defActionBar.setCustomView(R.layout.my_custom_ation_bar)

        val toolbar = defActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

        titleTxt = defActionBar.customView.findViewById(R.id.titleTxt)
        addBtn = defActionBar.customView.findViewById(R.id.addBtn)
    }

}