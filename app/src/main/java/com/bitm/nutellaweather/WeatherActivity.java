package com.bitm.nutellaweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {

    TextView showBio, temperatureTextView;
    //ArrayList<String> nameList;
    String bioStr;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        showBio=(TextView)findViewById(R.id.locationTextView);
        Intent intent=getIntent();
        bioStr=intent.getStringExtra("name");
        showBio.setText(bioStr);
        url = "http://api.openweathermap.org/data/2.5/weather?q="+bioStr+"&APPID=0fc72e22806f9941a2dcc84798a7598d";

        getPoetNames();
    }

    private void getPoetNames() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {

                    JSONArray array = response.getJSONArray("main");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String name = object.getString("temp");
                        temperatureTextView.setText(name);
                        //String bioL=object.getString("main");
                        //nameList.add(name);
                        //bio.add(bioL);



                    }
                    //ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this, android.R.layout.simple_list_item_1, nameList);
                    //listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError){
                    Toast.makeText(WeatherActivity.this, "NO Internet Connection", Toast.LENGTH_SHORT).show();
                }

                Log.i("Error", error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
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
    */
}
