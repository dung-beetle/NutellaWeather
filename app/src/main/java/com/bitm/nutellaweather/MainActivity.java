package com.bitm.nutellaweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView =( EditText )findViewById(R.id.EditText1);
    }

    public void showDetails( View view)
    {
        String button_test;
        button_test =((Button) view).getText().toString();
        if (button_test.equals("Search for Weather"))
        {
            Intent intent1= new Intent(this,WeatherActivity.class);
            startActivity(intent1 );
        }

        else if (button_test.equals("View Favourite List"))
        {
            Intent intent= new Intent(this,FavouriteActivity.class);
            startActivity(intent );
        }

    }
}