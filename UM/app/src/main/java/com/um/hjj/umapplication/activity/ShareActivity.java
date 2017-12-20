package com.um.hjj.umapplication.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.um.hjj.umapplication.R;
import com.um.hjj.umapplication.databinding.ActivityShareBinding;

public class ShareActivity extends AppCompatActivity {
    private ActivityShareBinding shareBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareBinding = DataBindingUtil.setContentView(this, R.layout.activity_share);
    }
}
