package com.example.akshayasrinivasan.navigationdrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Update extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CircleImageView image;

    TextView name, emaiL;
    CircleImageView img;
    byte[] image1;
    DatabaseAdapter DBAdap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView=navigationView.inflateHeaderView(R.layout.nav_header_main);
        name= (TextView) headerView.findViewById(R.id.textView);
        emaiL= (TextView) headerView.findViewById(R.id.Name);
        img=(CircleImageView) headerView.findViewById(R.id.imageView);




       /*name = (TextView) findViewById(R.id.Name);
        email = (TextView) findViewById(R.id.textView);
        img = (CircleImageView) findViewById(R.id.imageView);
*/
        image=(CircleImageView) findViewById(R.id.image6);
        image.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                selectImage();
            }

        });

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                builder.setTitle("Thank you!");
                builder.setMessage("Details have been successfully updated");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Update.this, MainActivity.class);
                        startActivity(intent);


                    }
                });

                builder.show();


            }

        });



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        Toast.makeText(Update.this, "byeee", Toast.LENGTH_LONG).show();



     /*  emaiL.setText(DR.getEMAIL());

       name.setText(DR.getFIRSTNAME());
       CircleImageView img=(CircleImageView) findViewById(R.id.image);
       img.setImageBitmap(DR.getIMAGE());*/


        DBAdap = new DatabaseAdapter(this);
        DBAdap = DBAdap.open();

        List<DataRegister> DR = DBAdap.getReadings();

        for (DataRegister dr : DR) {

            emaiL.setText(dr.getEMAIL());
            name.setText(dr.getFIRSTNAME());

        }



    }


    public void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                   /* File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);*/
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String em2=emaiL.getText().toString();

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                Toast.makeText(Update.this, "hiii", Toast.LENGTH_LONG).show();
                DBAdap.updateReportPicture(em2, thumbnail);


                image.setImageBitmap(DBAdap.getReportPicture(em2));


                img.setImageBitmap(DBAdap.getReportPicture(em2));

            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                // Log.w("......***********......", picturePath+"");

                DBAdap.updateReportPicture(em2, thumbnail);
                Toast.makeText(Update.this, "hiii2", Toast.LENGTH_LONG).show();

                image.setImageBitmap(DBAdap.getReportPicture(em2));
                img.setImageBitmap(DBAdap.getReportPicture(em2));


            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_appt) {
            Intent intent = new Intent(Update.this, LandingPage.class);
            startActivityForResult(intent, 0);

        } else if (id == R.id.nav_report) {


            Intent intent = new Intent(Update.this, measurement.class);
            startActivityForResult(intent, 0);
            this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

