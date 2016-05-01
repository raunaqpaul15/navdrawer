package com.example.akshayasrinivasan.navigationdrawer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LogPrinter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Context;
import android.app.Activity;
import android.view.LayoutInflater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity {

    private Bitmap bitmap;
    private ProgressDialog dialog;
    Uri imageUri;
    private String imgPath;
    private static final int PICK_IMAGE = 1;
    ImageView image;
    EditText text5;
    EditText editTextFirstName, editTextPassword, editTextConfirmPassword, editLastName, editDOB, editContact, editEmail;
    Button btnCreateAccount;
    Bitmap thumbnail;
    byte[] iimg=null;
    Context context = this;
    TextView log;
    CircleImageView img;

    DatabaseAdapter loginDataBaseAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDataBaseAdapter = new DatabaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        editTextFirstName = (EditText) findViewById(R.id.text3);
        editTextPassword = (EditText) findViewById(R.id.text8);
        editTextConfirmPassword = (EditText) findViewById(R.id.text9);
        editLastName = (EditText) findViewById(R.id.text4);
        editDOB = (EditText) findViewById(R.id.text5);
        editContact = (EditText) findViewById(R.id.text6);

        editEmail = (EditText) findViewById(R.id.text7);





        TextView link_to_login = (TextView) findViewById(R.id.link_to_login);
        link_to_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Login.class);
                startActivityForResult(intent, 0);
            }

        });

        //Button addphoto = (Button) findViewById(R.id.addphoto);


        text5 = (EditText) findViewById(R.id.text5);
        text5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                final DatePicker picker = new DatePicker(Register.this);
                picker.setCalendarViewShown(false);

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
                        int day = picker.getDayOfMonth();
                        int month = (picker.getMonth() + 1);
                        int year = picker.getYear();
                        text5.setText(day + "/" + month + "/" + year);

                    }
                });

                builder.show();
            }

        });


        btnCreateAccount = (Button) findViewById(R.id.btnProceed);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String firstName = editTextFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                String eMail = editEmail.getText().toString();
                String dob = editDOB.getText().toString();
                String contact = editContact.getText().toString();


                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText()
                        .toString();
                if (firstName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    loginDataBaseAdapter.insertEntry(firstName, lastName, eMail, dob, contact, password);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();

                    Intent intent = new Intent(Register.this,Login.class);
                    // intent.putExtra("firstname",editTextFirstName.getText().toString());
                    startActivity(intent);

                    finish();

                }
            }


        });
    }







    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

        }











    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/














