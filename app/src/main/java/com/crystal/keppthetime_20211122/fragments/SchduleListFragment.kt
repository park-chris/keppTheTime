package com.crystal.keppthetime_20211122.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crystal.keppthetime_20211122.EditAppointmentActivity
import com.crystal.keppthetime_20211122.R
import com.crystal.keppthetime_20211122.adapters.ScheduleRecyclerAdapter
import com.crystal.keppthetime_20211122.databinding.FragmentScheduleListBinding
import com.crystal.keppthetime_20211122.datas.BasicResponse
import com.crystal.keppthetime_20211122.datas.ScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchduleListFragment : BaseFragment() {

    lateinit var binding : FragmentScheduleListBinding

    val mScheduleList = ArrayList<ScheduleData>()

    lateinit var mScheduleAdapter : ScheduleRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_list, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddAppointment.setOnClickListener {

            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {

        getScheduleListFromServer()

        mScheduleAdapter = ScheduleRecyclerAdapter( mContext, mScheduleList )

        binding.appointmentRecyclerView.adapter = mScheduleAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    fun getScheduleListFromServer() {

        apiService.getReqeuestAppointment().enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

                    mScheduleList.clear()

                    mScheduleList.addAll(br.data.appointments)

                    mScheduleAdapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

    }

}