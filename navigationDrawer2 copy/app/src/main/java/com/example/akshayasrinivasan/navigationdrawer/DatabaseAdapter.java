package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/16/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import android.graphics.BitmapFactory;

public class DatabaseAdapter {
    static final String DATABASE_NAME = "VITALS.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 9;
    static final String CREATE_REGISTER = "create table " + DataRegister.TABLE + "( "
            + "ID" + " integer primary key autoincrement,"
            + "FIRSTNAME  text,LASTNAME text,PASSWORD text,DOB text,CONTACT text,EMAIL text, IMAGE text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;
    private String pass;
    private String contact;

    public DatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String firstName, String lastName, String password, String dob, String contact, String email) {
        ContentValues newValues = new ContentValues();
        newValues.put("FIRSTNAME", firstName);
        newValues.put("LASTNAME", lastName);
        newValues.put("PASSWORD", password);

        newValues.put("DOB", dob);
        newValues.put("CONTACT", contact);
        newValues.put("EMAIL", email);

        db.insert(DataRegister.TABLE, null, newValues);

    }

    public int deleteEntry(String email) {

        String where = "EMAIL=?";
        int numberOFEntriesDeleted = db.delete(DataRegister.TABLE, where,
                new String[]{email});
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String email) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DataRegister.TABLE, new String[]{"EMAIL",
                        "PASSWORD"}, "EMAIL=?",
                new String[]{email}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())

        {
            pass = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            cursor.close();
        }
        return pass;


        // return contact


    }


   /* public DataRegister getSinlgebyte() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DataRegister.TABLE, new String[]{"EMAIL",
                "FIRSTNAME", "IMAGE"}, null, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        String email=cursor.getString(cursor.getColumnIndex("EMAIL"));
        String fname=cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
        byte[] img=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
        cursor.close();
        return new DataRegister(Utility.getPhoto(img),fname,email);
        // return contact



    }*/

    public void updateEntry(String firstName, String lastName, String password, String dob, String contact, String email) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("FIRSTNAME", firstName);
        updatedValues.put("LASTNAME", lastName);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("DOB", dob);
        updatedValues.put("CONTACT", contact);
        updatedValues.put("EMAIL", email);
        String where = "EMAIL= ?";
        db.update(DataRegister.TABLE, updatedValues, where, new String[]{email});
    }

    public List<DataRegister> getReadings() {
        List<DataRegister> temp = new ArrayList<DataRegister>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT FIRSTNAME,EMAIL FROM " + DataRegister.TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                DataRegister t = new DataRegister();
                t.setFIRSTNAME(c.getString(0));
                t.setEMAIL(c.getString(1));


                temp.add(t);
            } while (c.moveToNext());
        }

        return temp;
    }

   /* public Bitmap getimage(String email)

    {

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DataRegister.TABLE, new String[] { "EMAIL", "IMAGE",
                      },  " EMAIL=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        cursor.moveToFirst();
        byte[] Image2 = cursor.getBlob(cursor.getColumnIndex("IMAGE"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Image2);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        // setImage(byteImage2);
        cursor.close();
        return bitmap;

    }*/


    public void updateReportPicture(String email, Bitmap picture) {
        // Saves the new picture to the internal storage with the unique identifier of the report as
        // the name. That way, there will never be two report pictures with the same name.
        String picturePath1 = "";
        File internalStorage = context.getDir("ReportPictures", Context.MODE_PRIVATE);
        File reportFilePath = new File(internalStorage, email + ".png");
        picturePath1 = reportFilePath.toString();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(reportFilePath);
            picture.compress(Bitmap.CompressFormat.PNG, 100 /*quality*/, fos);
            fos.close();
        } catch (Exception ex) {
            Log.i("DATABASE", "Problem updating picture", ex);
            picturePath1 = "";
        }

        // Updates the database entry for the report to point to the picture
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues newPictureValue = new ContentValues();
        newPictureValue.put("IMAGE",
                picturePath1);

        db.update(DataRegister.TABLE,
                newPictureValue,
                "EMAIL=?",
                new String[]{String.valueOf(email)});
    }


    public Bitmap getReportPicture(String email) {
        String picturePath2 = getReportPicturePath(email);
        if (picturePath2 == null || picturePath2.length() == 0)
            return (null);

        Bitmap reportPicture = BitmapFactory.decodeFile(picturePath2);

        return (reportPicture);
    }

    /**
     * Gets the path of the picture for the specified report in the database.
     *
     * @return the picture for the report, or null if no picture was found.
     */
    private String getReportPicturePath(String email) {
        // Gets the database in the current database helper in read-only mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // After the query, the cursor points to the first database row
        // returned by the request
        Cursor reportCursor = db.query(DataRegister.TABLE,
                null,
                "EMAIL=?",
                new String[]{String.valueOf(email)},
                null,
                null,
                null);
        if (reportCursor != null && reportCursor.getCount() > 0)
            reportCursor.moveToFirst();

        // Get the path of the picture from the database row pointed by
        // the cursor using the getColumnIndex method of the cursor.
        String picturePath = reportCursor.getString(reportCursor.getColumnIndex("IMAGE"));

        reportCursor.close();

        return (picturePath);
    }


    public String getContact(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DataRegister.TABLE, new String[]{"EMAIL",
                        "CONTACT"}, "EMAIL=?",
                new String[]{email}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())


        {
            contact = cursor.getString(cursor.getColumnIndex("CONTACT"));
            cursor.close();


            // return contact

        }
        return contact;

    }
}








