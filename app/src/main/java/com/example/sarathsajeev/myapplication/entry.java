package com.example.sarathsajeev.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class entry extends AppCompatActivity {


    Button update;
    AlertDialog.Builder builder;
    EditText Email,Date, Acc_transfer, Auto, Entertn, Family, Food, Health, Home, Household, Income, Loans, Personal, Tax, Travel, Uncaty, Util ;
    private ProgressDialog pDialog;
    private SessionManager session;
    //String Email= new String("");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Email=(EditText)findViewById(R.id.editText20);
        update =(Button)findViewById(R.id.button3);
        Date =(EditText)findViewById(R.id.editText4);
        Acc_transfer =(EditText)findViewById(R.id.editText5);
        //Auto =(EditText)findViewById(R.id.editText6);
        Entertn =(EditText)findViewById(R.id.editText7);
        Family =(EditText)findViewById(R.id.editText8);
        Food =(EditText)findViewById(R.id.editText9);
        //Health =(EditText)findViewById(R.id.editText10);
        //Home =(EditText)findViewById(R.id.editText11);
        Household =(EditText)findViewById(R.id.editText12);
        //Income =(EditText)findViewById(R.id.editText13);
        //Loans=(EditText)findViewById(R.id.editText14);
        //Personal=(EditText)findViewById(R.id.editText15);
        //Tax=(EditText)findViewById(R.id.editText16);
        Travel=(EditText)findViewById(R.id.editText17);
        Uncaty=(EditText)findViewById(R.id.editText18);
        Util=(EditText)findViewById(R.id.editText19);
        builder=new AlertDialog.Builder(entry.this);

       /* // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        //db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(registration.this,main.class);
            startActivity(intent);
            finish();
        }

        */





       // Email=session.getUserDetails();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email,date, acc_transfer, auto, entertn, family, food, health, home, household, income, loans, personal, tax, travel, uncaty, util ;
                email= Email.getText().toString();;
                date= Date.getText().toString();
                acc_transfer= Acc_transfer.getText().toString();
                //auto= Auto.getText().toString();
                entertn= Entertn.getText().toString();
                family= Family.getText().toString();
                food= Food.getText().toString();
                //health= Health.getText().toString();
                //home=  Home.getText().toString();
                household= Household.getText().toString();
                //income= Income.getText().toString();
                //loans= Loans.getText().toString();
                //personal= Personal.getText().toString();
                //tax= Tax.getText().toString();
                travel= Travel.getText().toString();
                uncaty= Uncaty.getText().toString();
                util= Util.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST,AppConfig.URL_ADD_EXPESENSE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("server response");
                                builder.setMessage("Response :"+response);
                                builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which){
                                        Email.setText("");
                                        Date.setText("");
                                        Acc_transfer.setText("");
                                        //Auto.setText("");
                                        Entertn.setText("");
                                        Family.setText("");
                                        Food.setText("");
                                        //Health.setText("");
                                        //Home.setText("");
                                        Household.setText("");
                                        //Income.setText("");
                                        //Loans.setText("");
                                        //Personal.setText("");
                                        //Tax.setText("");
                                        Travel.setText("");
                                        Uncaty.setText("");
                                        Util.setText("");



                                    }
                                });

                                AlertDialog alertDialog= builder.create();
                                alertDialog.show();



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(entry.this,"Error .. OOPs",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();


                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params= new HashMap<String, String>();
                        params.put("email",email);
                        params.put("date",date);
                        params.put("acc_transfer",acc_transfer);
                        //params.put("automobile",auto);
                        params.put("entertainment",entertn);
                        params.put("family",family);
                        params.put("food",food);
                        //params.put("health_care",health);
                       // params.put("home_office",home);
                        params.put("household",household);
                        //params.put("income",income);
                        //params.put("loans",loans);
                        //params.put("personal",personal);
                        //params.put("tax",tax);
                        params.put("travel",travel);
                        params.put("uncategorized",uncaty);
                        params.put("utillties",util);
                        return params;
                    }
                };
                MySingleton.getInstance(entry.this).addTorequestque(stringRequest);






            }

        });






    }
}
