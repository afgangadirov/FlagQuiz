package com.example.bayrakuygulamasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(@Nullable Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"bayraklar\" (\n" +
                "\t\"bayrak_id\"\tINTEGER,\n" +
                "\t\"bayrak_ad\"\tTEXT,\n" +
                "\t\"bayrak_resim\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"bayrak_id\" AUTOINCREMENT)\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bayraklar");
        onCreate(sqLiteDatabase);

    }
}
