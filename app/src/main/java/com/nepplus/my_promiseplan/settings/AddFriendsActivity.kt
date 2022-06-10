package com.nepplus.my_promiseplan.settings

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepplus.my_promiseplan.BasicActivity
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MyFriendsRecyclerAdapter
import com.nepplus.my_promiseplan.databinding.ActivityAddFriendsBinding
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.modles.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendsActivity : BasicActivity() {

    lateinit var binding : ActivityAddFriendsBinding
    lateinit var mFriendAdapter : MyFriendsRecyclerAdapter

    var mFriendList = ArrayList<UserData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.searchBtn.setOnClickListener {
//          서버 검색한 친구 목록 받아오기
            val inputNick = binding.searchEdt.text.toString()

//            이글자가 2자이상인가 > 통과
            if (inputNick.length < 2){
                Toast.makeText(mContext, "검색어는 2자 이상 작성해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            서버에 실제 검색어로 검색
            apiList.getRequestSearchUser(inputNick).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!

//                        리스트 삭제
                        mFriendList.clear()
//                        서버에서 내려준 새로운 리스트로 다시 덮어준다.
                        mFriendList.addAll(br.data.users)

//                        어댑터에 리스트가 바뀌었다는 통보
//                        RecyclerView의 모든 뷰를 삭제하고 다시 뷰를 생성 비효율적인 코드
                        mFriendAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }

    }

    override fun setValues() {
//          어댑터 초기화(init) => 빈껍데기로
        mFriendAdapter = MyFriendsRecyclerAdapter(mContext,mFriendList,"add")
        binding.findUserRecyclerView.adapter = mFriendAdapter
        binding.findUserRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
}