package com.nepplus.my_promiseplan.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.api.APIList
import com.nepplus.my_promiseplan.api.SeverApi
import com.nepplus.my_promiseplan.databinding.ListItemUserBinding
import com.nepplus.my_promiseplan.fragments.RequestFriendsListFragment
import com.nepplus.my_promiseplan.modles.BasicResponse
import com.nepplus.my_promiseplan.modles.UserData
import com.nepplus.my_promiseplan.settings.MyFriendsActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendsRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>,
    val type : String,
) : RecyclerView.Adapter<MyFriendsRecyclerAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val profileImg = view.findViewById<ImageView>(R.id.profileImg)
        val nicknameTxt = view.findViewById<TextView>(R.id.nicknameTxt)
        val addFriendBtn = view.findViewById<Button>(R.id.addFriendBtn)
        val acceptBtn = view.findViewById<Button>(R.id.acceptBtn)
        val denyBtn = view.findViewById<Button>(R.id.denyBtn)
        val requestLayoutBtn = view.findViewById<LinearLayout>(R.id.requestLayoutBtn)
        val socialLoginImg = view.findViewById<ImageView>(R.id.socialLoginImg)

        fun bind (item : UserData){

            val apiList = SeverApi.getRetrofit(mContext).create(APIList::class.java)

            Glide.with(mContext).load(item.profileImg).into(profileImg)
            nicknameTxt.text = item.nickname

            when (type){
                "add" -> {
                    addFriendBtn.visibility = View.VISIBLE
                    requestLayoutBtn.visibility = View.GONE
                }
                "requested" -> {
                    addFriendBtn.visibility = View.GONE
                    requestLayoutBtn.visibility = View.VISIBLE
                }
                "my" -> {
                    addFriendBtn.visibility = View.GONE
                    requestLayoutBtn.visibility = View.GONE
                }
            }

            when(item.provider){
                "kakao"->{
                    socialLoginImg.setImageResource(R.drawable.kakao_login_icon)
                }
                "facebook"->{
                    socialLoginImg.setImageResource(R.drawable.facebook_login_icon)
                }
                else ->{socialLoginImg.visibility = View.GONE}
            }

            val ocl = object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    val okOrNo = p0!!.tag.toString()

                    apiList.putRequestAnswerRequest(item.id,okOrNo).enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (!response.isSuccessful){
                                val errorBodyStr = response.errorBody()!!.string()
                                val jsonObj = JSONObject(errorBodyStr)
                                val message = jsonObj.getString("message")

                                Log.e("?????? ?????? ??????",message)
                            }
//                          ??????????????? ????????????(request Friends List) ?????? ???????????? ?????? ???????
//                          ????????? -> ???????????? : context?????? ??????

//                            ViewPager2?????? fragment??? ????????? ?????? ???????????? ?????? ??????
//                            ViewPager2????????? ????????? fragment??? ????????? ??? ??????. => tag????????? ????????????.
                            ((mContext as MyFriendsActivity)
                                .supportFragmentManager
                                .findFragmentByTag("f1")as RequestFriendsListFragment)
                                .getRequestedFriednsListFromServer()

                        }
                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                        }
                    })
                }
            }
            acceptBtn.setOnClickListener(ocl)
            denyBtn.setOnClickListener(ocl)

            addFriendBtn.setOnClickListener {
                apiList.postRequestAddFriend(item.id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(mContext, "${item.nickname}????????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.list_item_user,parent,false)
        return ItemViewHolder(row)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}