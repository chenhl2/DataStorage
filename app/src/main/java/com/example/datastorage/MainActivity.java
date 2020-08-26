package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.datastorage.sqlitedemo.SqlLiteActivity;

public class MainActivity extends AppCompatActivity {
    private Button mButton_go_sp,mButton_go_of,mButton_go_sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_go_sp = findViewById(R.id.go_sp_btn);
        mButton_go_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mGoSpIntent = new Intent(MainActivity.this,SharedPreferencesDemo.class);
                startActivity(mGoSpIntent);
            }
        });
        mButton_go_of = findViewById(R.id.go_of_btn);
        mButton_go_of.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mGoOfIntent = new Intent(MainActivity.this, OpenFileDemo.class);
                startActivity(mGoOfIntent);
            }
        });
        mButton_go_sqlite = findViewById(R.id.go_sqlite_btn);
        mButton_go_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mGoSqliteIntent = new Intent(MainActivity.this, SqlLiteActivity.class);
                startActivity(mGoSqliteIntent);
            }
        });


    }
}