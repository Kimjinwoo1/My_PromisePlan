package com.nepplus.my_promiseplan.modles

import com.google.gson.annotations.SerializedName

data class UserData(
    val id : Int,
    val provider : String,
    val email : String,
    @SerializedName("nick_name")
    val nickname : String,
    @SerializedName("profile_img")
    val profileImg : String,
    @SerializedName("ready_minute")
    val readyMinute : String,
) {
}