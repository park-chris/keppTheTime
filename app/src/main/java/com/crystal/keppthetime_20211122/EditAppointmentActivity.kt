package com.crystal.keppthetime_20211122

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import com.crystal.keppthetime_20211122.databinding.ActivityEditAppointmentBinding
import com.crystal.keppthetime_20211122.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

//    약속을 잡을 일시를 저장할 변수 (Calender)

    val mSelectedDateTime = Calendar.getInstance()      // 기본값 : 현재 일시

    lateinit var binding : ActivityEditAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {

//            날짜 선택 팝업 (DatePickerDialog) 사용 예시

//            선택 완료 시 할일 설정 (JAVA - Interface) => 변수에 담아두자

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

//                    실제로 날짜가 선택되면 할 일 적는 공간.

//                    Log.d("선택한 년 월 일", "${year}년 ${month}월 ${day}일")

//                    선택된 일시를 저장할 변수에, 연/월/일을 저장.

//                    mSelectedDateTime.set(Calendar.YEAR, year)
//                    mSelectedDateTime.set(Calendar.MONTH, month)
//                    mSelectedDateTime.set(Calendar.DAY_OF_MONTH, day)

//                    년/월/일을 한번에 저장하는 set 함수 활용
                    mSelectedDateTime.set(year, month, day)

//                    txtDate의 문구를 -> 2021-12-05 와  같은 양식으로 가공해서 텍스트 세팅.

//                    Calendar를 다룰 양식만 미리 지정.
                    val dateFormat = SimpleDateFormat( "yy년 M월 d일" )

//                    Calendar => String 변환.
                    val dateStr = dateFormat.format(mSelectedDateTime.time)

                    binding.txtDate.text = dateStr

                }

            }

//            실제 팝업창 띄우기

//            Kotlin : JAVA 기반 언어 => 월 : 0 ~ 11로 만들어져있음.

//            오늘 날짜를 기본으로 띄우도록. => mSelectedDateTime에 저장된 값 활용
//            선택한 이후는

            val datePickerDialog = DatePickerDialog(
                mContext, dateSetListener,
                mSelectedDateTime.get( Calendar.YEAR ),
                mSelectedDateTime.get( Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()

        }

        binding.btnOk.setOnClickListener {

            val inputTitle = binding.edtTitle.text.toString()
//            val inputDateTime = binding.edtDateTime.text.toString()
            val inputPlace = binding.edtPlace.text.toString()
            val inputLat = binding.edtLatitude.text.toString().toDouble()
            val inputLng = binding.edtLongitude.text.toString().toDouble()

            apiService.postRequestAppointment(inputTitle, "임시값", inputPlace, inputLat, inputLng).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }

    }

    override fun setValues() {
    }
}