package com.example.akshayasrinivasan.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class ForgotPassword extends AppCompatActivity {

    Button btnSubmit;
    EditText text10;


    DatabaseAdapter loginDataBaseAdapter;
    String email,to,pass,msg;
    otpgeneration password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Button btnConfirm=(Button)findViewById(R.id.btnConfirm);
        btnConfirm.setVisibility(View.GONE);

        final TextView otp =(TextView)findViewById(R.id.otp);
        otp.setVisibility(View.GONE);

        final EditText text11=(EditText)findViewById(R.id.text11);
        text11.setVisibility(View.GONE);

        text10=(EditText) findViewById(R.id.text10);


        loginDataBaseAdapter = new DatabaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();



        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                email = text10.getText().toString();
                to = loginDataBaseAdapter.getContact(email);
                Toast.makeText(ForgotPassword.this,to , Toast.LENGTH_LONG).show();
                int passwordSize = 6;
                password = new otpgeneration(passwordSize);
                pass = (password.get()).toString();
                msg="Your OTP is  " + pass + "  Please enter OTP now.  regards NworxHealth";
                sendsms job=new sendsms();
                job.execute(to);

                text11.setVisibility(view.VISIBLE);
                btnConfirm.setVisibility(view.VISIBLE);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ResetPassword.class);
                        startActivityForResult(intent, 0);




                    }

                });
                otp.setVisibility(view.VISIBLE);
            }

        });



    }

    private class sendsms extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            try {
                // Construct data
                String user = "username=" + "raunaq@nworxtech.com";
                String hash = "&hash=" + "b8fb0248e8ed3b7f32bd424a3f90ad49b3e3c178";
                String message = "&message=" + msg;
                String sender = "&sender=" + "nextgcare";
                String numbers = "&numbers=" + to;

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("http://api.txtlocal.com/send/?").openConnection();
                String data = user + hash + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();

                return stringBuffer.toString();
            } catch (Exception e) {
                System.out.println("Error SMS "+e);
                return "Error "+e;
            }

        }


    }

}


