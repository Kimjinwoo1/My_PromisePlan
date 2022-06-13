package com.nepplus.my_promiseplan.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.FragmentSettingBinding
import com.nepplus.my_promiseplan.dialogs.CustomAlertDialog
import com.nepplus.my_promiseplan.main.LoginActivity
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.settings.MyFriendsActivity
import com.nepplus.my_promiseplan.utils.ContextUtil
import com.nepplus.my_promiseplan.utils.GlobalData
import com.nepplus.my_promiseplan.utils.URIPathHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SettingsFragment : BaseFragment() {

    lateinit var binding : FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.profileImg.setOnClickListener {
            val pl = object : PermissionListener{
                override fun onPermissionGranted() {
                    val myIntent = Intent()

                    myIntent.action = Intent.ACTION_PICK
                    myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE

                    startForResult.launch(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                }

            }

            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .setDeniedMessage("[설정] > [권한]에서 갤러리 권한을 열어주세요.")
                .check()
        }
////          닉네임 변경 이벤트
//        binding.changeNickLayout.setOnClickListener {
//            val alert = CustomAlertDialog(mContext,requireActivity())
//            alert.myDialog()
//
//            alert.binding.titleTxt.text = "닉네임 변경"
//            alert.binding.bodytxt.visibility = View.GONE
//            alert.binding.contentEdt.hint = "변경할 닉네임을 입력해주세요."
//            alert.binding.contentEdt.inputType = InputType.TYPE_CLASS_TEXT
//
//            alert.binding.positiveBtn.setOnClickListener {
//                apiList.patchRequestEditUserInfo(
//                    "nickname",
//                    alert.binding.contentEdt.text.toString()
//                ).enqueue(object : Callback<BasicResponse>{
//                    override fun onResponse(
//                        call: Call<BasicResponse>,
//                        response: Response<BasicResponse>
//                    ) {
//                        if (response.isSuccessful){
//                            val br = response.body()!!
//
//                            GlobalData.loginUser = br.data.user
//
//                            setUserData()
//
//                            alert.dialog.dismiss()
//                        }
//                        else{
//                            val errorBodyStr = response.errorBody()!!.string()
//                            val jsonObj = JSONObject(errorBodyStr)
//                            val message = jsonObj.getString("message")
//
//                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//
//                    }
//                })
//            }
//            alert.binding.negativeBtn.setOnClickListener {
//                alert.dialog.dismiss()
//            }
//        }

    val ocl =object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val type = p0!!.tag.toString()

                val alert = CustomAlertDialog(mContext,requireActivity())
                alert.myDialog()

                when(type){
                    "nickname" ->{
                        alert.binding.titleTxt.text = "닉네임 변경"
                        alert.binding.contentEdt.hint = "변경할 닉네임 입력"
                        alert.binding.contentEdt.inputType = InputType.TYPE_CLASS_TEXT
                    }
                }
                alert.binding.bodytxt.visibility = View.GONE

                alert.binding.positiveBtn.setOnClickListener {
                    apiList.patchRequestEditUserInfo(
                        type,
                        alert.binding.contentEdt.text.toString()
                    ).enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (response.isSuccessful){
                                val br = response.body()!!

                                GlobalData.loginUser = br.data.user

                                setUserData()

                                alert.dialog.dismiss()
                            }

                            else{
                                val errorBodyStr = response.errorBody()!!.string()
                                val jsonObj = JSONObject(errorBodyStr)
                                val message = jsonObj.getString("message")

                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                        }
                    })
                }
                alert.binding.negativeBtn.setOnClickListener {
                    alert.dialog.dismiss()
                }
            }
        }
        binding.changeNickLayout.setOnClickListener(ocl)



//          비밀번호 변경이벤트
        binding.changePwLayout.setOnClickListener {

        }
//          개인 약속 이벤트
        binding.myWorkLayout.setOnClickListener {


        }
//          친구 목록 관리 이벤트
        binding.myFriendsLayout.setOnClickListener {
        val myIntent = Intent(mContext, MyFriendsActivity::class.java)
        startActivity(myIntent)
        }
//          로그아웃
        binding.logoutLayout.setOnClickListener {
            val alert = CustomAlertDialog(mContext,requireActivity())
            alert.myDialog()

            alert.binding.titleTxt.text = "로그아웃"
            alert.binding.bodytxt.text = "정말 로그아웃 하시겠습니까?"
            alert.binding.contentEdt.visibility = View.GONE
            alert.binding.positiveBtn.setOnClickListener {

                ContextUtil.clear(mContext)

                GlobalData.loginUser = null

                val myIntent = Intent(mContext, LoginActivity::class.java)
                myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(myIntent)

                alert.dialog.dismiss()

            }

            alert.binding.negativeBtn.setOnClickListener {
                alert.dialog.dismiss()
            }
        }

    }

    override fun setValues() {
        setUserData ()

        when(GlobalData.loginUser!!.provider){
            "Kakao" -> {
                binding.socialLogImg.setImageResource(R.drawable.kakao_login_icon)
            }
            "facebook" -> {
                binding.socialLogImg.setImageResource(R.drawable.facebook_login_icon)
            }
            else -> binding.socialLogImg.visibility = View.GONE
        }

    }

    fun setUserData(){
        Glide.with(mContext)
            .load(GlobalData.loginUser!!.profileImg)

            .into(binding.profileImg)
        binding.nickNameTxt.text = GlobalData.loginUser!!.nickname
    }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val dataUri = it.data?.data

          //  Glide.with(mContext).load(dataUri).into(binding.profileImg)

            val file = File(URIPathHelper().getPath(mContext, dataUri!!))

            val fileReqBody = RequestBody.create(MediaType.get("image/*"),file)
            val body = MultipartBody.Part.createFormData("profile_image","myFile.jpg",fileReqBody)

            apiList.putRequestUserImage(body).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        GlobalData.loginUser = response.body()!!.data.user

                        Glide.with(mContext).load(GlobalData.loginUser!!.profileImg).into(binding.profileImg)

                        Toast.makeText(mContext, "프로필 사진이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }
    }

}