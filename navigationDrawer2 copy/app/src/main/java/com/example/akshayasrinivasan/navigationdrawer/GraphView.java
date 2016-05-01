package com.example.akshayasrinivasan.navigationdrawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

public class GraphView extends AppCompatActivity {

    DatabaseAdapterMeasurements db;
    GraphicalView mChart;
    GraphicalView mChart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        db = new DatabaseAdapterMeasurements(getApplicationContext());






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
          renderer.setColor(Color.BLUE);
          renderer.setLineWidth(3);


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
          LinearLayout chart_container = (LinearLayout) findViewById(R.id.chart_container);

          mChart = ChartFactory.getLineChartView(this, dataset, mRenderer);
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
          renderer1.setColor(Color.BLUE);
          renderer1.setLineWidth(3);

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
          LinearLayout chart_container1 = (LinearLayout) findViewById(R.id.chart_container1);

          mChart1 = ChartFactory.getLineChartView(this, dataset1, mRenderer1);
          chart_container1.addView(mChart1);

          if (mChart1 == null) {
              Log.i("mChart1: ", "null");
          } else {
              Log.i("mChart1: ", "not null but not displaying graph");
          }

      }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
