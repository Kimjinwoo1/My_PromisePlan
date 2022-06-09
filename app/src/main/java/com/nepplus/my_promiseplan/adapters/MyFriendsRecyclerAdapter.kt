package com.nepplus.my_promiseplan.adapters

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.ListItemUserBinding

class MyFriendsRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<MyFriendsRecyclerAdapter.ItemViewHolder>() {

    lateinit var binding : ListItemUserBinding

    inner class ItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind (item : UserData){

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.list_item_user,
            parent,
            false
        )
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}