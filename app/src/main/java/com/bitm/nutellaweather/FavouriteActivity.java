package com.bitm.nutellaweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    ListView listView;
    String url = "http://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&APPID=0fc72e22806f9941a2dcc84798a7598d";
    ArrayList<String> nameList;
    ArrayList<String> bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        listView = (ListView) findViewById(R.id.ListView1);
        nameList = new ArrayList<>();
        bio = new ArrayList<>();
        getPoetNames();


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(FavouriteActivity.this,WeatherActivity.class);
                intent.putExtra("name",nameList.get(position).toString());
                startActivity(intent);
                Toast.makeText(FavouriteActivity.this, nameList.get(position).toString(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void getPoetNames() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {

                    JSONArray array = response.getJSONArray("list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String name = object.getString("name");
                        //String bioL=object.getString("main");
                        nameList.add(name);
                        //bio.add(bioL);



                    }
                    ArrayAdapter adapter = new ArrayAdapter(FavouriteActivity.this, android.R.layout.simple_list_item_1, nameList);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError){
                    Toast.makeText(FavouriteActivity.this, "NO Internet Connection", Toast.LENGTH_SHORT).show();
                }

                Log.i("Error", error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }
}

