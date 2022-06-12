package com.nepplus.my_promiseplan.modles

class DataResponse (
    val user : UserData,
    val token : String,
    val users : List<UserData>,
    val friends : List<UserData>,
    val appointment : AppointmentData,
        ) {
}