package com.um.hjj.umapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.um.hjj.umapplication.R;
import com.um.hjj.umapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        String platform = getIntent().getStringExtra(LoginActivity.PLATFORM);
        Toast.makeText(this, platform + "平台过来的", Toast.LENGTH_SHORT).show();
    }
}
