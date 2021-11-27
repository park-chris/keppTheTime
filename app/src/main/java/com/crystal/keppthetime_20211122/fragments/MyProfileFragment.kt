package com.crystal.keppthetime_20211122.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.crystal.keppthetime_20211122.R
import com.crystal.keppthetime_20211122.databinding.FragmentMyProfileBinding
import com.crystal.keppthetime_20211122.datas.BasicResponse
import com.crystal.keppthetime_20211122.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding : FragmentMyProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()

    }


    override fun setupEvents() {
    }

    override fun setValues() {

//        내 정보를 서버에서 받아오자. -> 이미지 반영 / 닉네임 반영

        getMyInfoFromServer()
//        1. 프래그먼트에서 retrofit 어떻게 활용?

//        2. dataBinding -> 프래그먼트에서는 어떻게 데이터바인딩?

    }

    fun getMyInfoFromServer() {
        apiService.getRequestMyInfo().enqueue(object  : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful ) {
                    val br = response.body()!!

//                    닉네임 / 프로필 이미지 주소 추출 => UI 반영?

                    binding.txtNickname.text = br.data.user.nickname

                    Glide.with(mContext).load(br.data.user.profileImageUrl).into(binding.imgProfile)
                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
            }


        })
    }

}