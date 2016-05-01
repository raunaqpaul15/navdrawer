package com.example.akshayasrinivasan.navigationdrawer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import android.os.Bundle;
import com.example.akshayasrinivasan.navigationdrawer.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class Tab2Fragment extends Fragment {

    DatabaseAdapterMeasurements db;
    GraphicalView mChart;
    GraphicalView mChart1;
    GraphicalView mChart2;
    GraphicalView mChart3;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        db = new DatabaseAdapterMeasurements(getContext());


        List<Measurements> temp = db.getAllTempReadings();


        // Line chart for BMI

        int arraySize = temp.size();
        Double tempArray[] = new Double[arraySize];
        int timeArray[] = new int[arraySize];


        //Log.d("ans", String.valueOf(temp.size()));


        for (Measurements t : temp) {


            String log = "Email: " + t.getEMAIL() + "BMI: " + t.getBMI() + "Date: " + t.getDATE();
            Log.d("Record" + t, log);
        }
        // Reading values into the array!

        for (int i = 0; i < arraySize; i++) {

            tempArray[i] = (temp.get(i).getBMI());

            timeArray[i] = Integer.parseInt(temp.get(i).getDATE());
            System.out.println("i: " + tempArray[i] + " ," + timeArray[i]);
        }
        TimeSeries series = new TimeSeries("BMI");


        for (int i = 0; i < tempArray.length; i++) {
            series.add(timeArray[i], tempArray[i]);

        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        //properties of line
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesTextSize(25);
        renderer.setColor(Color.rgb(26, 178, 255));
        renderer.setLineWidth(5);


        renderer.setFillPoints(true);
        renderer.setPointStyle(PointStyle.CIRCLE);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

        mRenderer.addSeriesRenderer(renderer);

        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setMarginsColor(Color.WHITE);


        mRenderer.setChartTitle("BMI Variation Graph");
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsColor(0, Color.BLACK);
        mRenderer.setPointSize(5);


        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setXTitle("Date");
        mRenderer.setYTitle("BMI");

        mRenderer.setAxisTitleTextSize(30);
        mRenderer.setShowLegend(false);
        mRenderer.setLabelsTextSize(15);


        for (int i = 0; i < tempArray.length; i++) {
            mRenderer.addXTextLabel(i, " :temp");

        }
        LinearLayout chart_container = (LinearLayout) v.findViewById(R.id.chart_container);

        mChart = ChartFactory.getLineChartView(getActivity(), dataset, mRenderer);
        chart_container.addView(mChart);

        if (mChart == null) {
            Log.i("mChart: ", "null");
        } else {
            Log.i("mChart: ", "not null but not displaying graph");
        }


// Line chart for Weight


        List<Measurements> temp1 = db.getAllWeightReadings();

        int arraySize1 = temp1.size();
        Double tempArray1[] = new Double[arraySize1];
        int timeArray1[] = new int[arraySize1];


        for (Measurements t : temp1) {


            String log = "Email: " + t.getEMAIL() + "Weight: " + t.getWEIGHT() + "Date: " + t.getDATE();
            Log.d("Record" + t, log);
        }
        // Reading values into the array!

        for (int i = 0; i < arraySize1; i++) {

            tempArray1[i] = (temp1.get(i).getWEIGHT());

            timeArray1[i] = Integer.parseInt(temp1.get(i).getDATE());
            System.out.println("i: " + tempArray1[i] + " ," + timeArray1[i]);
        }
        TimeSeries series1 = new TimeSeries("WEIGHT");


        for (int i = 0; i < tempArray1.length; i++) {
            series1.add(timeArray1[i], tempArray1[i]);

        }
        XYMultipleSeriesDataset dataset1 = new XYMultipleSeriesDataset();
        dataset1.addSeries(series1);

        //properties of line
        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setDisplayChartValues(true);
        renderer1.setChartValuesTextSize(25);
        renderer1.setColor(Color.rgb(26, 178, 255));
        renderer1.setLineWidth(5);

        renderer1.setFillPoints(true);
        renderer1.setPointStyle(PointStyle.CIRCLE);


        XYMultipleSeriesRenderer mRenderer1 = new XYMultipleSeriesRenderer();

        mRenderer1.addSeriesRenderer(renderer1);

        mRenderer1.setApplyBackgroundColor(true);
        mRenderer1.setBackgroundColor(Color.WHITE);
        mRenderer1.setMarginsColor(Color.WHITE);


        mRenderer1.setChartTitle("Weight Variation Graph");
        mRenderer1.setAxesColor(Color.BLACK);
        mRenderer1.setLabelsColor(Color.BLACK);
        mRenderer1.setXLabelsColor(Color.BLACK);
        mRenderer1.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer1.setYLabelsColor(0, Color.BLACK);
        mRenderer1.setPointSize(5);


        mRenderer1.setApplyBackgroundColor(true);
        mRenderer1.setXTitle("Date");
        mRenderer1.setYTitle("Weight");

        mRenderer1.setAxisTitleTextSize(30);
        mRenderer1.setShowLegend(false);
        mRenderer1.setLabelsTextSize(15);


        for (int i = 0; i < tempArray1.length; i++) {
            mRenderer1.addXTextLabel(i, " :temp1");

        }
        LinearLayout chart_container1 = (LinearLayout) v.findViewById(R.id.chart_container1);

        mChart1 = ChartFactory.getLineChartView(getActivity(), dataset1, mRenderer1);
        chart_container1.addView(mChart1);

        if (mChart1 == null) {
            Log.i("mChart1: ", "null");
        } else {
            Log.i("mChart1: ", "not null but not displaying graph");
        }




        // Line chart for Blood Pressure


        List<Measurements> temp2 = db.getAllBPReadings();

        int arraySize2 = temp2.size();
        Double tempArray2[] = new Double[arraySize2];
        int timeArray2[] = new int[arraySize2];


        for (Measurements t : temp2) {


            String log = "Email: " + t.getEMAIL() + "BP: " + t.getBLOODPRESSURE() + "Date: " + t.getDATE();
            Log.d("Record" + t, log);
        }
        // Reading values into the array!

        for (int i = 0; i < arraySize2; i++) {

            tempArray2[i] = (temp2.get(i).getBLOODPRESSURE());

            timeArray2[i] = Integer.parseInt(temp2.get(i).getDATE());
            System.out.println("i: " + tempArray2[i] + " ," + timeArray2[i]);
        }
        TimeSeries series2 = new TimeSeries("BLOODPRESSURE");


        for (int i = 0; i < tempArray2.length; i++) {
            series2.add(timeArray2[i], tempArray2[i]);

        }
        XYMultipleSeriesDataset dataset2 = new XYMultipleSeriesDataset();
        dataset2.addSeries(series2);

        //properties of line
        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setDisplayChartValues(true);
        renderer2.setChartValuesTextSize(25);
        renderer2.setColor(Color.rgb(26, 178, 255));
        renderer2.setLineWidth(5);

        renderer2.setFillPoints(true);
        renderer2.setPointStyle(PointStyle.CIRCLE);


        XYMultipleSeriesRenderer mRenderer2 = new XYMultipleSeriesRenderer();

        mRenderer2.addSeriesRenderer(renderer1);

        mRenderer2.setApplyBackgroundColor(true);
        mRenderer2.setBackgroundColor(Color.WHITE);
        mRenderer2.setMarginsColor(Color.WHITE);


        mRenderer2.setChartTitle("BP Variation Graph");
        mRenderer2.setAxesColor(Color.BLACK);
        mRenderer2.setLabelsColor(Color.BLACK);
        mRenderer2.setXLabelsColor(Color.BLACK);
        mRenderer2.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer2.setYLabelsColor(0, Color.BLACK);
        mRenderer2.setPointSize(5);


        mRenderer2.setApplyBackgroundColor(true);
        mRenderer2.setXTitle("Date");
        mRenderer2.setYTitle("BP");

        mRenderer2.setAxisTitleTextSize(30);
        mRenderer2.setShowLegend(false);
        mRenderer2.setLabelsTextSize(15);


        for (int i = 0; i < tempArray2.length; i++) {
            mRenderer2.addXTextLabel(i, " :temp2");

        }
        LinearLayout chart_container2 = (LinearLayout) v.findViewById(R.id.chart_container2);

        mChart2 = ChartFactory.getLineChartView(getActivity(), dataset2, mRenderer2);
        chart_container2.addView(mChart2);

        if (mChart2 == null) {
            Log.i("mChart2: ", "null");
        } else {
            Log.i("mChart2: ", "not null but not displaying graph");
        }



        // Line chart for Glucose Level


        List<Measurements> temp3 = db.getGlucose();

        int arraySize3 = temp3.size();
        Double tempArray3[] = new Double[arraySize3];
        int timeArray3[] = new int[arraySize3];


        for (Measurements t : temp3) {


            String log = "Email: " + t.getEMAIL() + "Glucose Level: " + t.getGLUCOSELEVEL() + "Date: " + t.getDATE();
            Log.d("Record" + t, log);
        }
        // Reading values into the array!

        for (int i = 0; i < arraySize3; i++) {

            tempArray3[i] = (temp3.get(i).getGLUCOSELEVEL());

            timeArray3[i] = Integer.parseInt(temp3.get(i).getDATE());
            System.out.println("i: " + tempArray3[i] + " ," + timeArray3[i]);
        }
        TimeSeries series3 = new TimeSeries("GLUCOSELEVEL");


        for (int i = 0; i < tempArray3.length; i++) {
            series3.add(timeArray3[i], tempArray3[i]);

        }
        XYMultipleSeriesDataset dataset3 = new XYMultipleSeriesDataset();
        dataset3.addSeries(series3);

        //properties of line
        XYSeriesRenderer renderer3 = new XYSeriesRenderer();
        renderer3.setDisplayChartValues(true);
        renderer3.setChartValuesTextSize(25);
        renderer3.setColor(Color.rgb(26, 178, 255));
        renderer3.setLineWidth(5);

        renderer3.setFillPoints(true);
        renderer3.setPointStyle(PointStyle.CIRCLE);


        XYMultipleSeriesRenderer mRenderer3 = new XYMultipleSeriesRenderer();

        mRenderer3.addSeriesRenderer(renderer1);

        mRenderer3.setApplyBackgroundColor(true);
        mRenderer3.setBackgroundColor(Color.WHITE);
        mRenderer3.setMarginsColor(Color.WHITE);


        mRenderer3.setChartTitle("Glucose Variation Graph");
        mRenderer3.setAxesColor(Color.BLACK);
        mRenderer3.setLabelsColor(Color.BLACK);
        mRenderer3.setXLabelsColor(Color.BLACK);
        mRenderer3.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer3.setYLabelsColor(0, Color.BLACK);
        mRenderer3.setPointSize(5);


        mRenderer3.setApplyBackgroundColor(true);
        mRenderer3.setXTitle("Date");
        mRenderer3.setYTitle("Glucose");

        mRenderer3.setAxisTitleTextSize(30);
        mRenderer3.setShowLegend(false);
        mRenderer3.setLabelsTextSize(15);


        for (int i = 0; i < tempArray3.length; i++) {
            mRenderer3.addXTextLabel(i, " :temp3");

        }
        LinearLayout chart_container3 = (LinearLayout) v.findViewById(R.id.chart_container3);

        mChart3 = ChartFactory.getLineChartView(getActivity(), dataset3, mRenderer3);
        chart_container3.addView(mChart3);

        if (mChart3 == null) {
            Log.i("mChart3: ", "null");
        } else {
            Log.i("mChart3: ", "not null but not displaying graph");
        }

        return v;


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



}
