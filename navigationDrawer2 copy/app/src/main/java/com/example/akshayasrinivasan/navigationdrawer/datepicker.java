package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/20/16.
 */
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;

@SuppressLint("ValidFragment")
public class datepicker extends DialogFragment  implements DatePickerDialog.OnDateSetListener {
    //EditText txtdate;
   /* public datepicker(View view){
        txtdate=(EditText)view;

    }*/
    public Dialog onCreateDialog(Bundle savedInstanceState) {


// Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //show to the selected date in the text box
        String date=day+"-"+(month+1)+"-"+year;
        //txtdate.setText(date);
    }



}

