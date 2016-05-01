package com.example.akshayasrinivasan.navigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import android.os.Bundle;
import com.example.akshayasrinivasan.navigationdrawer.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import android.widget.EditText;

import android.app.FragmentTransaction;

import android.view.View.OnFocusChangeListener;

public class Tab1Fragment extends Fragment {

    private Button btPlacesAPI;
    private TextView tvPlaceAPI;
    public String inputtext;
    GridView gridView;
    static final String[] timeslots = new String[]{
            "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00",
            "17:00", "18:00", "19:00", "20:00", "21:00",
            "22:00", "23:00"};

    private int PLACE_PICKER_REQUEST = 1;
    private GoogleApiClient client;
    private EditText txtdate;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab1, container, false);

        txtdate=(EditText) v.findViewById(R.id.txtdate);
        tvPlaceAPI=(TextView) v.findViewById(R.id.tv_place_id);

        btPlacesAPI=(Button) v.findViewById(R.id.bt_ppicker);


        txtdate.setOnClickListener(new View.OnClickListener()

                                   {

                                       public void onClick (View v){


                                           AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                           final DatePicker picker = new DatePicker(getActivity());
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
                                                   txtdate.setText(day + "/" + month + "/" + year);

                                               }
                                           });

                                           builder.show();
                                       }

        });







        btPlacesAPI.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick (View view){

                                               PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                                               try {

                                                   startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);


                                               } catch (GooglePlayServicesRepairableException e) {
                                                   e.printStackTrace();
                                               } catch (GooglePlayServicesNotAvailableException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }

        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client=new GoogleApiClient.Builder(getActivity()).addApi(AppIndex.API).build();






    return v;
    }



       @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                String toastMsg = String.format(
                        "Place:%s \n" +
                                "Alamat:%s \n" +
                                "Latlng%s \n", place.getName(), place.getAddress(), place.getLatLng().latitude + " " + place.getLatLng().longitude);
                tvPlaceAPI.setText(toastMsg);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.akshayasrinivasan.navigationdrawer/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

       /* EditText txtDate = (EditText) getView().findViewById(R.id.txtdate);
        txtDate.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    datepicker dialog = new datepicker(view);
                    FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");
                }
            }
        });*/
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, timeslots);
        final EditText txtTime = (EditText) getView().findViewById(R.id.txt_time);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView = (GridView) getView().findViewById(R.id.gridview1);
                gridView.setVisibility(View.VISIBLE);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        inputtext = ((TextView) v).getText().toString();
                        txtTime.setText(inputtext);
                        gridView.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.akshayasrinivasan.navigationdrawer/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }



}
