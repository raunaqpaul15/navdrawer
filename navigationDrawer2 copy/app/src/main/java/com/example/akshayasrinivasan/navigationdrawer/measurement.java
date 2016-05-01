package com.example.akshayasrinivasan.navigationdrawer;

/**
 * Created by AkshayaSrinivasan on 3/22/16.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class measurement extends AppCompatActivity {
    Button button;
    TextView HEIGHT, WEIGHT, BMI, BLOODPRESSURE, PULSE, TEMPERATURE, GLUCOSELEVEL, OXYGENLEVEL;
    EditText Date, Email, Ref;
    DatabaseAdapterMeasurements MeasurementsDataBaseAdapter;
    Button graph;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        graph=(Button)findViewById(R.id.graph);
        graph.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         Intent intent = new Intent(measurement.this, LandingPage.class);
                                         startActivity(intent);

                                     }
                                 });

        Date = (EditText) findViewById(R.id.Date);

        HEIGHT = (TextView) findViewById(R.id.HEIGHT);
        WEIGHT = (TextView) findViewById(R.id.WEIGHT);
        BMI = (TextView) findViewById(R.id.BMI);
        BLOODPRESSURE = (TextView) findViewById(R.id.BP);
        PULSE = (TextView) findViewById(R.id.PULSE);

        TEMPERATURE = (TextView) findViewById(R.id.TEMP);
        GLUCOSELEVEL = (TextView) findViewById(R.id.GL);
        Email = (EditText) findViewById(R.id.Email);
        Ref = (EditText) findViewById(R.id.Reference);
        OXYGENLEVEL = (TextView) findViewById(R.id.OL);

        int index = 0;
        button = (Button)

                findViewById(R.id.button);

        MeasurementsDataBaseAdapter = new

                DatabaseAdapterMeasurements(this);

        MeasurementsDataBaseAdapter = MeasurementsDataBaseAdapter.open();
        Measurements studentCourse = new Measurements();


        studentCourse.setHEIGHT("160 cm");
        studentCourse.setWEIGHT(56.0);
        studentCourse.setBMI(21.0);
        studentCourse.setBLOODPRESSURE(120.0);
        studentCourse.setPULSE("75");
        studentCourse.setTEMPERATURE("98.2 F");
        studentCourse.setGLUCOSELEVEL(154.5);
        studentCourse.setOXYGENLEVEL("90%");
        studentCourse.setEMAIL("alex@gmail.com");
        studentCourse.setDATE("2016");
        studentCourse.setREFERENCE("lkj");
        MeasurementsDataBaseAdapter.insertEntry(studentCourse);


        studentCourse.setHEIGHT("154 cm");
        studentCourse.setWEIGHT(58.2);
        studentCourse.setBMI(21.1);
        studentCourse.setBLOODPRESSURE(135.2);
        studentCourse.setPULSE("65");
        studentCourse.setTEMPERATURE("98.6 F");
        studentCourse.setGLUCOSELEVEL(150.4);
        studentCourse.setOXYGENLEVEL("80%");
        studentCourse.setEMAIL("alex@gmail.com");
        studentCourse.setDATE("2017");
        studentCourse.setREFERENCE("uhu");
        MeasurementsDataBaseAdapter.insertEntry(studentCourse);


        studentCourse.setHEIGHT("1671");
        studentCourse.setWEIGHT(55.0);
        studentCourse.setBMI(21.3);
        studentCourse.setBLOODPRESSURE(125.3);
        studentCourse.setPULSE("A-");
        studentCourse.setTEMPERATURE("A-");
        studentCourse.setGLUCOSELEVEL(178.4);
        studentCourse.setOXYGENLEVEL("A-");
        studentCourse.setEMAIL("cc@HOTMAIL.COM");
        studentCourse.setDATE("2018");
        studentCourse.setREFERENCE("lol");
        MeasurementsDataBaseAdapter.insertEntry(studentCourse);


        studentCourse.setHEIGHT("1091vvv");
        studentCourse.setWEIGHT(53.7);
        studentCourse.setBMI(21.0);
        studentCourse.setBLOODPRESSURE(136.2);
        studentCourse.setPULSE("A-");
        studentCourse.setTEMPERATURE("A-");
        studentCourse.setGLUCOSELEVEL(160.2);
        studentCourse.setOXYGENLEVEL("A-");
        studentCourse.setEMAIL("hhfg@HOTMAIL.COM");
        studentCourse.setDATE("2019");
        studentCourse.setREFERENCE("aaa");
        MeasurementsDataBaseAdapter.insertEntry(studentCourse);
        button.setOnClickListener(new View.OnClickListener()

                                  {

                                      public void onClick(View v) {
                                          AlertDialog.Builder builder = new AlertDialog.Builder(measurement.this);
                                          final DatePicker picker = new DatePicker(measurement.this);
                                          picker.setCalendarViewShown(false);

                                          builder.setTitle("Create Year");
                                          builder.setView(picker);
                                          builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  dialog.dismiss();


                                              }
                                          });
                                          builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {


                                                  String refer = Ref.getText().toString();
                                                  int day = picker.getDayOfMonth();
                                                  int month = (picker.getMonth() + 1);
                                                  int year = picker.getYear();
                                                  Date.setText(+day + "/" + month + "/" + year);

                                                  String date = Date.getText().toString();
                                                  String storeddate = MeasurementsDataBaseAdapter.getSinlgeEntry(refer);
                                                  System.out.println(date);


                                                  if (date.equals(storeddate)) {
                                                      System.out.println(date);
                                                      List<Measurements> measurements = MeasurementsDataBaseAdapter.getAllMeasurements(refer);


                                                      for (Measurements mn : measurements) {
                   /* if (date.equals("")) {
                        Toast.makeText(measurement.this, "field empty", Toast.LENGTH_LONG).show();
                    } else if (date.equals(mn.getDATE()))
*/

                                                          Email.setText(mn.getEMAIL());
                                                          HEIGHT.setText(mn.getHEIGHT());
                                                          //WEIGHT.setText(mn.getWEIGHT());
                                                          //BMI.setText(mn.getBMI());
                                                          //BLOODPRESSURE.setText(mn.getBLOODPRESSURE());
                                                          PULSE.setText(mn.getPULSE());
                                                          TEMPERATURE.setText(mn.getTEMPERATURE());
                                                          //GLUCOSELEVEL.setText(mn.getGLUCOSELEVEL());
                                                          OXYGENLEVEL.setText(mn.getOXYGENLEVEL());


                                                          // Toast.makeText(measurement.this, picker.getDayOfMonth() + " / " + (picker.getMonth() + 1) + " / " + picker.getYear(), Toast.LENGTH_LONG).show();


                    /* else {
                        Toast.makeText(measurement.this, "not exist", Toast.LENGTH_LONG).show();
                    }


                }*/
                                                      }
                                                  } else {
                                                      Toast.makeText(measurement.this, "Does not exist", Toast.LENGTH_LONG).show();
                                                  }


                                              }
                                          });
                                          builder.show();


                                      }


                                  }

        );


        /*Date.addTextChangedListener(new TextWatcher() {


            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {





            }

            public void afterTextChanged(Editable s) {
                System.out.println(s);
                //String date = s.toString();



                }



        });*/




    }

    /*@Override
    public void onBackPressed() {
        this.finish();
        moveTaskToBack(true);
    }*/
}














