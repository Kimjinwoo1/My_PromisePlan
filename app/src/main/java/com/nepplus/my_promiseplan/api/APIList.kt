package com.nepplus.my_promiseplan.api

import com.nepplus.my_promiseplan.modles.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIList {

//    user
    @GET("/user/check")
    fun getRequestUserCheck (
    @Query("type") type : String,
    @Query("value") value : String,
    ) : Call<BasicResponse>



}