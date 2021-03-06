package com.nepplus.my_promiseplan.api

import com.nepplus.my_promiseplan.modles.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface APIList {
//    delete
    @DELETE("/appointment")
    fun deleteRequest(
    @Query("appointment_id") appointmentid : Int,
    ) : Call<BasicResponse>

//    appointment
    @GET("/appointment")
    fun getRequestMyAppointment() : Call<BasicResponse>


    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAddAppointment(
    @Field("title") title : String,
    @Field("datetime") datetime : String,
    @Field("place") place : String,
    @Field("latitude") latitude: Double,
    @Field("longitude") longitude: Double,
    @Field("friend_list") friendlist : String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/appointment")
    fun putRequestAddAppointment(
        @Field("title") title : String,
        @Field("appointment_id") appointmentId  : Int,
        @Field("datetime") datetime : String,
        @Field("place") place : String,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
        @Field("friend_list") friendlist : String,
    ) : Call<BasicResponse>

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
    fun putRequestUserImage(@Part profileImg : MultipartBody.Part) : Call<BasicResponse>

//    ?????? ???????????? ??????
    @FormUrlEncoded
    @PATCH("/user/password")
    fun patchRequestPwChange(
    @Field("current_password") currentPassword : String,
    @Field("new_password") newPassword : String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/social")
    fun postRequestSocialLogin(
        @Field("provider") provider : String,
        @Field("uid") uid : String,
        @Field("nick_name")nickname : String,
    ) : Call<BasicResponse>

//  ???????????? ???????????? API
    @GET("/user/friend")
    fun getRequestMyFriendList(@Query("type")type : String): Call<BasicResponse>

//  ????????????????????? APi
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