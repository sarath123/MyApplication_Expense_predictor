package com.example.sarathsajeev.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class chart extends AppCompatActivity {

    BarChart barChart;
    Button button;
    int acc_transfer;
    int entertainment;
    int family;
    int food;
    int household;
    int travel;
    int uncategorized;
    int utillties;
    String desc ="X axis shows categories and Y axis shows expense";
    String text ="Please Click Refresh to load ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        button=(Button)findViewById(R.id.bn_update);
        button.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {


            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, AppConfig.URL_CHART, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                acc_transfer=response.getInt("acc_transfer");
                                entertainment=response.getInt("entertainment");
                                family=response.getInt("family");
                                food=response.getInt("food");
                                household=response.getInt("household");
                                travel=response.getInt("travel");
                                uncategorized=response.getInt("uncategorized");
                                utillties=response.getInt("utilities");

                            }
                            catch(JSONException e) {
                                e.printStackTrace();}

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(chart.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(chart.this).addTorequestque(jsonObjectRequest);


            barChart= (BarChart) findViewById(R.id.bargraph);
            ArrayList<BarEntry> barEntries = new ArrayList<>();
            barEntries.add(new BarEntry(acc_transfer,0));
            barEntries.add(new BarEntry(entertainment,1));
            barEntries.add(new BarEntry(family,2));
            barEntries.add(new BarEntry(food,3));
            barEntries.add(new BarEntry(household,4));
            barEntries.add(new BarEntry(travel,5));
            barEntries.add(new BarEntry(uncategorized,6));
            barEntries.add(new BarEntry(utillties,7));

            BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");


            ArrayList<String> cate = new ArrayList<>();
            cate.add("Account Transfer");
            cate.add("Entertainment");
            cate.add("Family");
            cate.add("Food");
            cate.add("Household");
            cate.add("Travel");
            cate.add("Uncategorized");
            cate.add("Utilities");


            BarData theData = new BarData(cate,barDataSet);
            barChart.setData(theData);
            barDataSet.setColors(new int[] { R.color.red, R.color.green, R.color.blue, R.color.yellow });
            barChart.setTouchEnabled(true);
            barChart.setDragEnabled(true);
            barChart.fitScreen();
            barChart.setDescription(desc);
            barChart.setNoDataText(text);
            barChart.setDrawHighlightArrow(true);
    }
});
    }
}