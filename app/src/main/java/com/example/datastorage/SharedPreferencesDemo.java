package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SharedPreferencesDemo extends AppCompatActivity {
    private Button mStorageDataButton;
    private Button mGetDataButton;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_demo);
        initView();

        mStorageDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSharedPreferences = getSharedPreferences("username",MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("name","ddd");
                Toast.makeText(SharedPreferencesDemo.this,"用户名已存储",Toast.LENGTH_SHORT).show();
                editor.commit();
            }
        });

        mGetDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mGetDataSf = getSharedPreferences("username", MODE_PRIVATE);
                String name = mGetDataSf.getString("name","null");
                Toast.makeText(SharedPreferencesDemo.this,name,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        mStorageDataButton = findViewById(R.id.storage_data_btn);
        mGetDataButton = findViewById(R.id.get_data_btn);
    }
}