package com.nepplus.my_promiseplan.api

import android.content.Context
import com.nepplus.my_promiseplan.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeverApi {

    companion object{


//        서버주소
        private val baseUrl = "https://keepthetime.xyz"

        private var retrofit : Retrofit? = null

        fun getRetrofit(context : Context) : Retrofit {

            if (retrofit == null){

                val interceptor = Interceptor {
                    with(it){
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token",ContextUtil.getLoginToken(context))
                            .build()
                        proceed(newRequest)
                    }
                }

                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}