package com.example.datastorage.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.datastorage.R;

public class SqlLiteActivity extends AppCompatActivity {
    private Button mCreateBookBtn, mAddDataBtn, mUpdateDataBtn, mDeleteDataBtn, mQueryDataBtn;
    private MyDatabaseHelper myDatabaseHelper;
    private static final String TAG = "SqlLiteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
//        myDatabaseHelper = new MyDatabaseHelper(this, "Book.db", null, 1);
        myDatabaseHelper = new MyDatabaseHelper(this, "Book.db", null, 2);
        mCreateBookBtn = findViewById(R.id.create_book_button);
        mCreateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        mAddDataBtn = findViewById(R.id.add_data_button);
        mAddDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                // 组装第一条数据
                contentValues.put("name", "疯狂android讲义");
                contentValues.put("author", "李刚");
                contentValues.put("pages", 454);
                contentValues.put("price", 66.66);
                // 插入第一条数据
                db.insert("Book", null, contentValues);

                contentValues.clear();
                // 组装第二条数据
                contentValues.put("name", "疯狂java讲义");
                contentValues.put("author", "李刚");
                contentValues.put("pages", 345);
                contentValues.put("price", 55.55);
                // 插入第二条数据
                db.insert("Book", null, contentValues);
            }
        });

        mUpdateDataBtn = findViewById(R.id.update_data_button);
        mUpdateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price", 99.99);
                db.update("Book", contentValues, "name = ?", new String[]{"疯狂android讲义"});
            }
        });

        mDeleteDataBtn = findViewById(R.id.delete_data_button);
        mDeleteDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"400"});
            }
        });

        mQueryDataBtn = findViewById(R.id.query_data_button);
        mQueryDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
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
        });
    }
}