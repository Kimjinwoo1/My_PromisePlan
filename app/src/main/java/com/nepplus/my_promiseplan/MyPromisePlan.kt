package com.nepplus.my_promiseplan

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyPromisePlan : Application() {

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "94464b925fe5735e13b18e1c8a8512a8")
    }
}