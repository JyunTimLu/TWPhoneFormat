package com.tim.phonenumberformattw;

import com.tim.phonenumberformattw.databinding.ActivityMainBinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tim.twphonenumberformat.TWPhoneFormatTextWatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewModel = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel.editText.addTextChangedListener(new TWPhoneFormatTextWatcher(viewModel.editText));
    }
}
