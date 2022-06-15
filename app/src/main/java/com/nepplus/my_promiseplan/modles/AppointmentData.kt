package com.nepplus.my_promiseplan.modles

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class AppointmentData (
    val id : Int,
    val title : String,
    val datetime : Date,
    @SerializedName("start_place")
    val startPlace : String,
    val place : String,
    val user : UserData,
    @SerializedName("invited_friends")
    val invitedFriends : List<UserData>
        ) :Serializable {
}