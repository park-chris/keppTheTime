package com.crystal.keppthetime_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.crystal.keppthetime_20211122.databinding.ActivitySignUpBinding
import com.crystal.keppthetime_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnOk.setOnClickListener {

//            입력값 추출

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()


//            회원가입 기능 API 호출 -> 응답 메세지 확인 (성공시)

            apiService.putRequestSignUp(inputEmail, inputPw, inputNickname).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {

                        val basicResponse = response.body()!!

                        Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {


                }


            })




        }

    }

    override fun setValues() {
    }
}