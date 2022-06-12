package com.nepplus.my_promiseplan.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.nepplus.my_promiseplan.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

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

//               gson에서 날짜 양식을 어떻게 파싱할 건지 => 추가 기능을 가진 gson으로 생성
                val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")

//                시차 보정기를 보조도구로 선택
                    .registerTypeAdapter(Date::class.java,DateDeserializer())
                    .create()

                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!
        }
    }
}