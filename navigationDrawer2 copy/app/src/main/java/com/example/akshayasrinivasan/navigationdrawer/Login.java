package com.example.akshayasrinivasan.navigationdrawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.widget.EditText;

import java.io.File;

public class Login extends AppCompatActivity {

    Button btnSignIn;
    TextView txtSignUp;
    DatabaseAdapter loginDataBaseAdapter;
    SharedPreferences validation;
    SharedPreferences.Editor editorValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDataBaseAdapter = new DatabaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        txtSignUp = (TextView) findViewById(R.id.link_to_register);




        txtSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(V.getContext(), Register.class);
                startActivityForResult(intent, 0);
            }

        });

        TextView link_to_forgot = (TextView) findViewById(R.id.link_to_forgot);
        link_to_forgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(V.getContext(), ForgotPassword.class);
                startActivityForResult(intent, 0);
            }

        });




        final EditText editTextUserName = (EditText) findViewById(R.id.text1);
        final EditText editTextPassword = (EditText) findViewById(R.id.text2);



        Button btnSignIn = (Button) findViewById(R.id.btnLogin);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String email = editTextUserName.getText().toString();

                validation = getSharedPreferences("myShaPreferences", Context.MODE_PRIVATE);
                String password = editTextPassword.getText().toString();
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(email);
                if (password.equals(storedPassword)) {
                    Toast.makeText(Login.this,
                            "Congrats: Login Successful", Toast.LENGTH_LONG)
                            .show();








                }
                else {
                    Toast.makeText(Login.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }

                if (validation.getBoolean("firsttime", false))
                {
                    Intent intent = new Intent(Login.this,measurement.class);
                    startActivity(intent);
                }


                else {
                    editorValidation=validation.edit();
                    editorValidation.putBoolean("firsttime", true);
                    editorValidation.apply();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);



                }

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }






}
