package com.crystal.keppthetime_20211122.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crystal.keppthetime_20211122.R
import com.crystal.keppthetime_20211122.datas.UserData

class MyFriendsAdapter(

    val mContext: Context,
    val resId: Int,
    val mList: ArrayList<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null ) {
            tempRow = mInflater.inflate(R.layout.my_friend_list, null)
        }

        val row = tempRow!!

        val data = mList[position]

        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = row.findViewById<TextView>(R.id.txtNickname)

        txtNickname.text = data.nickname
        Glide.with(mContext).load(data.profileImageUrl).into(imgProfile)


        return row
    }



}