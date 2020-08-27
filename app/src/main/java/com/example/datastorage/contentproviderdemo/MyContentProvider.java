package com.example.datastorage.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datastorage.sqlitedemo.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {

    private Context mContext;
    MyDatabaseHelper mDatabaseHelper = null;
    SQLiteDatabase db = null;

    // 设置ContentProvider的唯一标识
    public static final String AUTHORITY = "com.example.datastorage";

    protected static final int Book_Code = 1;
    protected static final int Category_Code = 2;

    private static final UriMatcher mMatcher = new UriMatcher(UriMatcher.NO_MATCH);






    @Override
    public boolean onCreate() {
        // 利用UriMatcher类在ContentProvider中注册URI
        mMatcher.addURI(AUTHORITY,"Book",Book_Code);
        mMatcher.addURI(AUTHORITY,"Category",Category_Code);

        // 在ContentProvider创建时对数据库进行初始化，运行在主线程，不能做耗时操作，此处仅仅做演示
        mContext = getContext();
        mDatabaseHelper = new MyDatabaseHelper(mContext, "Book.db", null, 2);
        db = mDatabaseHelper.getWritableDatabase();


        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String table = getTableName(uri);
        return db.query(table,strings,s,strings1,s1,null,null,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String table = getTableName(uri);
        db.insert(table,null,contentValues);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    // 利用传入的uri匹配对应的ContentProvider中的表
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case Book_Code:
                tableName = "Book";
                break;
            case Category_Code:
                tableName = "Category";
                break;
            default:
                // todo
                break;
        }
        return tableName;
    }
}
