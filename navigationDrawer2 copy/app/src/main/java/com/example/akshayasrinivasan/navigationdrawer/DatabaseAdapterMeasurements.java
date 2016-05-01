package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/22/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseAdapterMeasurements {
    static final String DATABASE_NAME = "VITALS.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 10;


    static final String CREATE_MEASUREMENTS = "create table " + Measurements.TABLE + " ( "
            + "PID" + " integer primary key autoincrement,"
            + " EMAIL text,HEIGHT real,WEIGHT real,BMI real,BLOODPRESSURE real,PULSE real,TEMPERATURE real, GLUCOSELEVEL real, OXYGENLEVEL real , DATE text, REFERENCE real);";


    public SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;

    public DatabaseAdapterMeasurements(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public DatabaseAdapterMeasurements open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public Cursor fetchAlldata() {
        return db.query(Measurements.TABLE, new String[]{"EMAIL", "DATE", "REFERENCE", "HEIGHT", "WEIGHT", "BMI", "BLOODPRESSURE", "PULSE", "TEMPERATURE", "GLUCOSELEVEL", "OXYGENLEVEL"}, null, null, null, null, null);
    }


    public void insertEntry(Measurements measurement) {
        ContentValues newValues = new ContentValues();


        newValues.put("HEIGHT", measurement.getHEIGHT());
        newValues.put("WEIGHT", measurement.getWEIGHT());
        newValues.put("BMI", measurement.getBMI());
        newValues.put("BLOODPRESSURE", measurement.getBLOODPRESSURE());
        newValues.put("PULSE", measurement.getPULSE());
        newValues.put("TEMPERATURE", measurement.getTEMPERATURE());
        newValues.put("GLUCOSELEVEL", measurement.getGLUCOSELEVEL());
        newValues.put("OXYGENLEVEL", measurement.getOXYGENLEVEL());
        newValues.put("EMAIL", measurement.getEMAIL());
        newValues.put("DATE", measurement.getDATE());
        newValues.put("REFERENCE", measurement.getREFERENCE());
        db.insert(Measurements.TABLE, null, newValues);

    }

    public int deleteEntry(String reference) {

        String where = "REFERENCE=?";
        int numberOFEntriesDeleted = db.delete(Measurements.TABLE, where,
                new String[]{reference});
        return numberOFEntriesDeleted;
    }

    public List<Measurements> getAllMeasurements(String refer) {

        List<Measurements> measurementsList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();




        Cursor cursor =null;
        cursor=db.rawQuery(" SELECT HEIGHT,WEIGHT,BMI,BLOODPRESSURE,PULSE,TEMPERATURE,GLUCOSELEVEL,OXYGENLEVEL,EMAIL FROM " + Measurements.TABLE + " WHERE REFERENCE =? ",new String[]{refer + ""});
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurements measurement = new Measurements();


                measurement.setHEIGHT(cursor.getString(0));
                measurement.setWEIGHT(cursor.getDouble(1));
                measurement.setBMI(cursor.getDouble(2));
                measurement.setBLOODPRESSURE(cursor.getDouble(3));
                measurement.setPULSE(cursor.getString(4));
                measurement.setTEMPERATURE(cursor.getString(5));
                measurement.setGLUCOSELEVEL(cursor.getDouble(6));
                measurement.setOXYGENLEVEL(cursor.getString(7));
                measurement.setEMAIL(cursor.getString(8));


                measurementsList.add(measurement);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return measurementsList;

    }


    public List<Measurements> getAllTempReadings() {
        List<Measurements> temp = new ArrayList<Measurements>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
      String selectQuery = "SELECT EMAIL,BMI,DATE FROM " + Measurements.TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
      if (c.moveToFirst()) {
           do {
               Measurements t = new Measurements();
                t.setEMAIL(c.getString(0));
               t.setBMI(c.getDouble(1));
               t.setDATE(c.getString(2));

              temp.add(t);
             } while (c.moveToNext());
         }

    return temp;
    }



    public List<Measurements> getAllWeightReadings() {
        List<Measurements> temp = new ArrayList<Measurements>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT EMAIL,WEIGHT,DATE FROM " + Measurements.TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Measurements t = new Measurements();
                t.setEMAIL(c.getString(0));
                t.setWEIGHT(c.getDouble(1));
                t.setDATE(c.getString(2));

                temp.add(t);
            } while (c.moveToNext());
        }

        return temp;
    }


    public List<Measurements> getAllBPReadings() {
        List<Measurements> temp = new ArrayList<Measurements>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT EMAIL,BLOODPRESSURE,DATE FROM " + Measurements.TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Measurements t = new Measurements();
                t.setEMAIL(c.getString(0));
                t.setBLOODPRESSURE(c.getDouble(1));
                t.setDATE(c.getString(2));

                temp.add(t);
            } while (c.moveToNext());
        }

        return temp;
    }


    public List<Measurements> getGlucose() {
        List<Measurements> temp = new ArrayList<Measurements>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT EMAIL,GLUCOSELEVEL,DATE FROM " + Measurements.TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Measurements t = new Measurements();
                t.setEMAIL(c.getString(0));
                t.setGLUCOSELEVEL(c.getDouble(1));
                t.setDATE(c.getString(2));

                temp.add(t);
            } while (c.moveToNext());
        }

        return temp;
    }

    public String getSinlgeEntry(String refer) {

        Cursor cursor = db.query(Measurements.TABLE, null, " REFERENCE=?",
                new String[]{refer}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String date = cursor.getString(cursor.getColumnIndex("DATE"));
        cursor.close();
        return date;
    }
}










