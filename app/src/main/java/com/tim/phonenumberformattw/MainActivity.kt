package com.tim.phonenumberformattw

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tim.phonenumberformattw.databinding.ActivityMainBinding

import tim.twphonenumberformat.TWPhoneFormat
import tim.twphonenumberformat.TWPhoneFormatTextWatcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.editText.addTextChangedListener(TWPhoneFormatTextWatcher(binding.editText))
        Log.d("TAG", TWPhoneFormat.getNumber("0933123456"))
    }
}
