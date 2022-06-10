package com.nepplus.my_promiseplan.api

import com.nepplus.my_promiseplan.modles.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    search
    @GET("/search/user")
    fun getRequestSearchUser(@Query("nickname") nickname: String) : Call<BasicResponse>

    //    user
    @GET("/user")
    fun getRequestMyInfo (): Call<BasicResponse>

    @FormUrlEncoded
    @PATCH("/user")
    fun patchRequestEditUserInfo(
        @Field("field") field : String,
        @Field("value") value: String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email")email : String,
        @Field("password")password : String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
     @Field("email") email : String,
     @Field("password") password : String,
     @Field("nick_name") nickname : String,
    ) : Call<BasicResponse>

    @GET("/user/check")
    fun getRequestUserCheck (
    @Query("type") type : String,
    @Query("value") value : String,
    ) : Call<BasicResponse>

    @Multipart
    @PUT("/user/image")
    fun RequestUserImage(@Part profileImg : MultipartBody.Part) : Call<BasicResponse>

//  친구목록 불러오기 API
    @GET("/user/friend")
    fun getRequestMyFriendList(@Query("type")type : String): Call<BasicResponse>

//  친구추가보내는 APi
    @FormUrlEncoded
    @POST("/user/friend")
    fun postRequestAddFriend(@Field("user_id")userId : Int) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user/friend")
    fun putRequestAnswerRequest(
        @Field("user_id") userId : Int,
        @Field("type") type : String,
    ): Call<BasicResponse>



}