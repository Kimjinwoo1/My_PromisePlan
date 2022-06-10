package com.nepplus.my_promiseplan.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.adapters.MyFriendsRecyclerAdapter
import com.nepplus.my_promiseplan.databinding.FragmentMyFriendsListBinding
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.modles.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendsListFragment : BaseFragment() {

    lateinit var binding : FragmentMyFriendsListBinding

    lateinit var mFriendAdapter : MyFriendsRecyclerAdapter
    var mFriendList = ArrayList<UserData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_friends_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setValues()
    }
    override fun onResume() {
        super.onResume()

    }
    override fun setEvents() {

    }

    override fun setValues() {
        mFriendAdapter = MyFriendsRecyclerAdapter(mContext,mFriendList,"my")
        binding.myFriendsRecyclerView.adapter = mFriendAdapter
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    fun getMyFriendsListFromSever(){
        apiList.getRequestMyFriendList("my").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    val br = response.body()!!

                    mFriendList.clear()
                    mFriendList.addAll(br.data.friends)
                    mFriendAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

}