package com.crystal.keppthetime_20211122.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crystal.keppthetime_20211122.R
import com.crystal.keppthetime_20211122.ViewPlaceMapActivity
import com.crystal.keppthetime_20211122.datas.ScheduleData
import com.naver.maps.map.overlay.InfoWindow

class ScheduleRecyclerAdapter(val mContext: Context, val mList: List<ScheduleData>) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder>(){

    inner class ScheduleViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        val txtAppointmentTitle = row.findViewById<TextView>(R.id.txtAppointmentTitle)
        val txtAppointmentPlace = row.findViewById<TextView>(R.id.txtAppointmentPlace)
        val txtDateTime = row.findViewById<TextView>(R.id.txtDateTime)
        val imgMap = row.findViewById<ImageView>(R.id.imgMap)

        fun bind( data: ScheduleData ) {

            txtAppointmentTitle.text = data.title
            txtAppointmentPlace.text = data.place

            txtDateTime.text = data.getFormattedDatetime()

            imgMap.setOnClickListener {

                val myIntent = Intent(mContext, ViewPlaceMapActivity::class.java)

//                어떤 약속을 보러가는지 데이터 첨부
                myIntent.putExtra("schedule", data)

                mContext.startActivity(myIntent)


//                추가기능 체험 - 정보창 (말풍선) => 마커에 반영



            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.schedule_list_item, parent, false)
        return ScheduleViewHolder(row)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        holder.bind(mList[position])


    }

    override fun getItemCount() = mList.size

}