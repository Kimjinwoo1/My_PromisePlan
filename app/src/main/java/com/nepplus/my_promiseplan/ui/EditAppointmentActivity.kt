package com.nepplus.my_promiseplan.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MyFriendSpinnerAdapter
import com.nepplus.my_promiseplan.databinding.ActivityEditAppointmentBinding
import com.nepplus.my_promiseplan.dialogs.CustomAlertDialog
import com.nepplus.my_promiseplan.modles.AppointmentData
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.modles.UserData
import com.nepplus.my_promiseplan.utils.SIzeUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BasicActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

    //      선택할 약속 일시를 저장할 맴버변수
    var mSelectedDateTime = Calendar.getInstance()

    //    친구 목록을 담고있는 Spinner 관련 변수
    var mFriendsList = ArrayList<UserData>()
    lateinit var mFriendsSpinnerAdapter: MyFriendSpinnerAdapter
    var mSelectedFriendsList = ArrayList<UserData>()

    var appointmentData: AppointmentData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        appointmentData = intent.getSerializableExtra("appointmentData") as AppointmentData?
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        수정시 삭제 버튼
        binding.deleteBtn.setOnClickListener {
            val alert = CustomAlertDialog(mContext,this)
            alert.myDialog()

            alert.binding.titleTxt.text = "삭제"
            alert.binding.bodytxt.text = "정말 삭제 하시겠습니까?"
            alert.binding.contentEdt.visibility = View.GONE
            alert.binding.positiveBtn.setOnClickListener {

                apiList.deleteRequest(appointmentData!!.id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(mContext, "약속이 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })

                alert.dialog.dismiss()

            }

            alert.binding.negativeBtn.setOnClickListener {
                alert.dialog.dismiss()
            }
        }
//        날짜 선택
        binding.dateTxt.setOnClickListener {
//            날짜를 선택하고 할 일(인터페이스)를 작성
            val dl = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    mSelectedDateTime.set(year, month, day)

                    val sdf = SimpleDateFormat("yyyy. M. d (E)")
                    Log.d("선택된 시간", sdf.format(mSelectedDateTime.time))

                    binding.dateTxt.text = sdf.format(mSelectedDateTime.time)
                }
            }
//            DataPickerdialog 팝업
            val dpd = DatePickerDialog(
                mContext, dl,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        //            시간 선택
        binding.timeTxt.setOnClickListener {
            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
                    mSelectedDateTime.set(Calendar.HOUR_OF_DAY, hour)
                    mSelectedDateTime.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("a h:mm")
                    binding.timeTxt.text = sdf.format(mSelectedDateTime.time)
                }
            }

            TimePickerDialog(
                mContext, tsl,
                mSelectedDateTime.get(Calendar.HOUR_OF_DAY),
                mSelectedDateTime.get(Calendar.MINUTE),
                false
            ).show()

        }
//      친구초대 버튼 클릭 이벤트 처리
        binding.invitedFriendBtn.setOnClickListener {
//      지금 선택된 친구가 누구인지 확인 => 스피너의 선택되어있는 아이템의 포지션 확인
            val selectedFriend = mFriendsList[binding.invitedFriendSpinner.selectedItemPosition]
//            지금 선택된 친구가 이미 선택이 되었는지 확인
            if (mSelectedFriendsList.contains(selectedFriend)) {
                Toast.makeText(mContext, "이미 추가한 친구입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            텍스트뷰 하나를 코틀린에서 생성
            val textView = TextView(mContext)

//            텍스트뷰에 패딩을 설정
            textView.setPadding(SIzeUtil.dpToPx(mContext, 5f).toInt())

            textView.text = selectedFriend.nickname

            //            만들어낸 텍스트뷰에 이벤트 처리
            textView.setOnClickListener {
                binding.friendListLayout.removeView(textView)
                mSelectedFriendsList.remove(selectedFriend)

                if (mSelectedFriendsList.size == 0) {
                    binding.friendListLayout.visibility = View.GONE
                }
            }

            binding.friendListLayout.visibility = View.VISIBLE
            binding.friendListLayout.addView(textView)
            mSelectedFriendsList.add(selectedFriend)

        }


//        약속 추가 이벤트
        binding.addBtn.setOnClickListener {
            val inputTitle = binding.titleEdt.text.toString()
//            약속제목 정하기
            if (inputTitle.isBlank()) {
                Toast.makeText(mContext, "약속을 정해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            약속명 정하기
            val inputPlaceName = binding.placeNameTxt.text.toString()
            if (inputPlaceName.isBlank()) {
                Toast.makeText(mContext, "약속 장소명을 정해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.dateTxt.text == "일자 선택") {
                Toast.makeText(mContext, "약속 일자를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.timeTxt.text == "시간 선택") {
                Toast.makeText(mContext, "약속 시간을 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            지금시간과 선택된(mSelectedDataTime)과의 시간차를 계산
            if (mSelectedDateTime.timeInMillis < Calendar.getInstance().timeInMillis) {
                Toast.makeText(mContext, "현재 시간이후의 시간으로 선택해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var friendListStr = ""

//              friendListStr에 들어갈 String을 선택된 친구목록을 이용해 가공
            for (friend in mSelectedFriendsList) {
                friendListStr += friend.id
                friendListStr += ","
            }

//                마지막 , 만 제거 => 글자가 0보다 커야 가능
            if (friendListStr != "") {
                friendListStr = friendListStr.substring(0, friendListStr.length - 1)
            }

//            서버에서 요구한 약속일시 양식대로 변환하여 전달

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")


            //            서버에 내 시간정보(Date)를 올릴때도, UTC로 변환하여 통신
            sdf.timeZone = TimeZone.getTimeZone("UTC")

            if (appointmentData == null){
                apiList.postRequestAddAppointment(
                    inputTitle,
                    sdf.format(mSelectedDateTime.time),
                    inputPlaceName,
                    0.0,
                    0.0,
                    friendListStr
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(mContext, "약속이 등록되었습니다.", Toast.LENGTH_SHORT).show()
//                            Log.d("현재 올린 약속 정보", response.body()!!.data.appointment.toString())
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
            else{
                apiList.putRequestAddAppointment(
                    inputTitle,
                    appointmentData!!.id,
                    sdf.format(mSelectedDateTime.time),
                    inputPlaceName,
                    0.0,
                    0.0,
                    friendListStr
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(mContext, "약속이 수정되었습니다.", Toast.LENGTH_SHORT).show()
//                            Log.d("현재 올린 약속 정보", response.body()!!.data.appointment.toString())
                            finish()
                        }
                        else{
                            val errorStr = response.errorBody()!!.string()
                            val JsonObj = JSONObject(errorStr)
                            val message = JsonObj.getString("message")
                            Log.d("호출실패",message)
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
        }
    }

    override fun setValues() {
        titleTxt.text = "새 약속 만들기"
        if (appointmentData != null){
            setData()
        }
        mFriendsSpinnerAdapter =
            MyFriendSpinnerAdapter(mContext, R.layout.list_item_user, mFriendsList)
        binding.invitedFriendSpinner.adapter = mFriendsSpinnerAdapter
        getMyFriendsListServer()

    }

    fun getMyFriendsListServer() {
        apiList.getRequestMyFriendList("my").enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    mFriendsList.clear()
                    mFriendsList.addAll(response.body()!!.data.friends)

                    mFriendsSpinnerAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    fun setData() {
        titleTxt.text = "약속 수정하기"
//      수정하기 타이틀
//        binding.timeTxt.text = "약속 수정하기"
        val appointData = appointmentData!!
//      수정하기 약속 제목 입력
        binding.titleEdt.setText(appointData.title)

//        일자 시간 수정하기 로직
        val sdf = SimpleDateFormat("yyyy. M. d (E)")
        val sdfTime = SimpleDateFormat("a h:mm")

        mSelectedDateTime.time = appointData.datetime
        binding.dateTxt.text = sdf.format(appointData.datetime)
        binding.timeTxt.text = sdfTime.format(appointData.datetime)

        for (friend in appointData.invitedFriends) {
            if (friend != appointData.user) {
                //            텍스트뷰 하나를 코틀린에서 생성
                val textView = TextView(mContext)

//            텍스트뷰에 패딩을 설정
                textView.setPadding(SIzeUtil.dpToPx(mContext, 5f).toInt())

                textView.text = friend.nickname

                binding.friendListLayout.visibility = View.VISIBLE
                binding.friendListLayout.addView(textView)
                mSelectedFriendsList.add(friend)
            }
        }
//        장소 입력하기 로직
        binding.placeNameTxt.setText(appointData.place)
//      수정 버튼
        binding.addBtn.text = "약속 수정하기"
//      삭제 버튼 생성
        binding.deleteBtn.visibility = View.VISIBLE
    }
}
