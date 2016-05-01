package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/20/16.
 */
import java.util.Calendar;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.Dialog;
import android.app.TimePickerDialog;

public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    private static EditText txt_time;
    public static TimeDialog newInstance(View view)
    {
        txt_time=(EditText)view;
        return(new TimeDialog());
    }
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute,false);

    }
    public void onTimeSet(TimePicker picker,int hour, int minute)
    {
        txt_time.setText(hour+":"+minute);
    }
}
