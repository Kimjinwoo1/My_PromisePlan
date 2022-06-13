package com.nepplus.my_promiseplan.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nepplus.my_promiseplan.databinding.ListItemAppointmentBinding
import com.nepplus.my_promiseplan.modles.AppointmentData
import java.text.SimpleDateFormat

class MyAppointmentRecyclerViewAdapter (
    val mContext : Context,
    val mList : List<AppointmentData>,
    val isInvited : Boolean,
) : RecyclerView.Adapter<MyAppointmentRecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder (val binding : ListItemAppointmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : AppointmentData) {

            binding.titleTxt.text = item.title

            val sdf = SimpleDateFormat("M/d a h:mm")

            if (isInvited){
                binding.invitedLayout.visibility = View.GONE
                binding.invitedFriendTxt.text = item.user.nickname
                Glide.with(mContext).load(item.user.profileImg).into(binding.invitedFriendImg)
            }

            binding.timeTxt.text = "${sdf.format(item.datetime)}"
            binding.placeNameTxt.text = "약속 장소 : ${item.place}"
            binding.memberCountTxt.text = "참여 인원 : ${item.invitedFriends.size}명"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemAppointmentBinding
                .inflate(LayoutInflater.from(mContext), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}