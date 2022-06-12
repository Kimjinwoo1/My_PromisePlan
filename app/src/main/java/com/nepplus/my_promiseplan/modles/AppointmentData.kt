package com.nepplus.my_promiseplan.modles

import com.google.gson.annotations.SerializedName

class AppointmentData (
    val id : Int,
    val title : String,
    val datetime : String,
    @SerializedName("start_place")
    val startPlace : String,
    val place : String,
    val user : UserData,
    @SerializedName("invited_friends")
    val invitedFriends : List<UserData>
        ) {
}