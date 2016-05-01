package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/16/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DatabaseAdapter.CREATE_REGISTER);
        _db.execSQL(DatabaseAdapterMeasurements.CREATE_MEASUREMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + DataRegister.TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + Measurements.TABLE);
        onCreate(_db);
    }

}

