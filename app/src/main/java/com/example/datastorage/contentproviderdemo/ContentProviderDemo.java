package com.example.datastorage.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.datastorage.R;

public class ContentProviderDemo extends AppCompatActivity {
    private static final String TAG = "ContentProviderDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_demo);
        // 练习ContentUris类的两个方法所用
        // uriPractise();
        Uri uri = Uri.parse("content://com.example.datastorage/Book");
        // 插入数据
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "ContentProvider练习");
        contentValues.put("author", "张三");
        contentValues.put("pages", 999);
        contentValues.put("price", 9.99);

        // 获取ContentResolver
        ContentResolver contentResolver = getContentResolver();
        // 向ContentProvider中插入数据
        contentResolver.insert(uri,contentValues);
        // 向ContentProvider中查询数据
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "book name is " + name);
                Log.d(TAG, "book author is " + author);
                Log.d(TAG, "book pages is " + pages);
                Log.d(TAG, "book price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();



    }

    private void uriPractise() {
        Uri uri = Uri.parse("content://com.example.datastorage/Book");
        Uri uriAppend = ContentUris.withAppendedId(uri, 1);
        Log.d(TAG, "uriPractise: withAppendedId-"+ uriAppend);
        Uri uri2 = Uri.parse("content://com.example.datastorage/Book/2");
        long BookId = ContentUris.parseId(uri2);
        Log.d(TAG, "uriPractise: parseId-"+BookId);






    }
}