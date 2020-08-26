package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OpenFileDemo extends AppCompatActivity {
    private static final String TAG = "OpenFileDemo";
    private TextView mShowNoFmtTV,mShowWithFmtTV;
    private Button mStorageNoFmtBtn,mStorageWithFmtBtn,mShowNoFmtBtn,mShowWithFmtBtn;
    private String FILE_NAME_NO_FORMATION = "no_formation_file";
    private String FILE_NAME_WITH_FORMATION = "with_formation_file.txt";
    private String TEXT_NO_FORMATION = "这是没有格式的文件里的内容";
    private String TEXT_WITH_FORMATION = "这是有格式的文件里的内容";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_demo);
        mShowNoFmtTV = findViewById(R.id.show_file_no_formation_textView);
        mShowWithFmtTV = findViewById(R.id.show_format_file_textView2);
        mStorageNoFmtBtn = findViewById(R.id.storage_no_formation_button);
        mStorageWithFmtBtn = findViewById(R.id.storage_with_formation_button);
        mShowNoFmtBtn = findViewById(R.id.show_file_no_formation_button);
        mShowWithFmtBtn = findViewById(R.id.show_format_file_button);

        mStorageNoFmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILE_NAME_NO_FORMATION, Context.MODE_PRIVATE);

                    fileOutputStream.write(TEXT_NO_FORMATION.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    mShowNoFmtTV.setText("出现文件找不到异常");
                } catch (IOException e) {
                    e.printStackTrace();
                    mShowNoFmtTV.setText("出现IO异常");
                }
            }
        });

        mStorageWithFmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream2 = openFileOutput(FILE_NAME_WITH_FORMATION, Context.MODE_PRIVATE);

                    fileOutputStream2.write(TEXT_WITH_FORMATION.getBytes());
                    fileOutputStream2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    mShowWithFmtTV.setText("出现了文件找不到异常");
                } catch (IOException e) {
                    e.printStackTrace();
                    mShowWithFmtTV.setText("出现了IO异常");
                }
            }
        });

        mShowNoFmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = openFileInput(FILE_NAME_NO_FORMATION);

                    byte[] buf = new byte[1024];
                    int hasRead = 0;
                    StringBuffer stringBuffer = new StringBuffer("");

                    // 读取文件
                    while ((hasRead = fileInputStream.read(buf)) > 0) {
                        stringBuffer.append(new String(buf,0,hasRead));
                    }
                    fileInputStream.close();
                    mShowNoFmtTV.setText(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    mShowNoFmtTV.setText("出现文件找不到异常");
                } catch (IOException e) {
                    e.printStackTrace();
                    mShowNoFmtTV.setText("出现了IO异常");
                }
            }
        });

        mShowWithFmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream2 = openFileInput(FILE_NAME_WITH_FORMATION);

                    byte[] buf = new byte[1024];
                    int hasRead = 0;
                    StringBuffer stringBuffer = new StringBuffer("");

                    while ((hasRead = fileInputStream2.read(buf)) > 0) {
                        stringBuffer.append(new String(buf,0,hasRead));
                    }

                    fileInputStream2.close();
                    mShowWithFmtTV.setText(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    mShowWithFmtTV.setText("出现文件找不到异常");
                } catch (IOException e) {
                    e.printStackTrace();
                    mShowWithFmtTV.setText("出现IO异常");
                }

            }
        });

//        Log.d(TAG, "onCreate: "+getFilesDir().toString());
//        File file = new File(getFilesDir(), "file_test.txt");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//
//            fileOutputStream.write(TEXT_WITH_FORMATION.getBytes());
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Log.e(TAG, "onCreate: "+Environment.getDataDirectory().toString());
        Log.e(TAG, "onCreate: "+getFilesDir().getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+getCacheDir().getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+getDir("test",MODE_PRIVATE).getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+Environment.getStorageDirectory().getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+Environment.getExternalStorageDirectory().getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+Environment.getExternalStoragePublicDirectory("").getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+getExternalFilesDir("").getAbsoluteFile().toString());
        Log.e(TAG, "onCreate: "+getExternalCacheDir().getAbsoluteFile().toString());



    }
}