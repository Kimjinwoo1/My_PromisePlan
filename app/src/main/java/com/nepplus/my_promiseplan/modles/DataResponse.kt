package com.nepplus.my_promiseplan.modles

import com.google.gson.annotations.SerializedName

data class DataResponse (
    val user : UserData,
    val token : String,
    val users : List<UserData>,
    val friends : List<UserData>,
    val appointment : AppointmentData,
    val appointments : List<AppointmentData>,
    @SerializedName("invited_appointments")
    val invitedAppointments : List<AppointmentData>,
        ) {
}