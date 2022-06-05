package com.nepplus.my_promiseplan.utils

import android.content.Context

class ContextUtil {

    companion object{
        private val prefName = "My_PromisePlan"
        private val Login_Token = "Login_Token"
        private val AUTO_Login = "AUTO_LOGIN"



        fun setLoginToken(context : Context, token : String ){
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(Login_Token,token).apply()
        }

        fun getLoginToken(context : Context) : String{
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getString(Login_Token,"")!!
        }

        fun setAutoLogin(context : Context, autoLogin : Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_Login, autoLogin).apply()
        }
        fun getAutoLogin(context : Context): Boolean{
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_Login,false)
        }

        fun clear (context : Context){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
    }

}