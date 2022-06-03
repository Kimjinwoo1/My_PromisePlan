package com.nepplus.my_promiseplan.api

import com.nepplus.my_promiseplan.modles.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
     @Field("email") email : String,
     @Field("password") password : String,
     @Field("nick_name") nickname : String,
    ) : Call<BasicResponse>

//    user
    @GET("/user/check")
    fun getRequestUserCheck (
    @Query("type") type : String,
    @Query("value") value : String,
    ) : Call<BasicResponse>



}