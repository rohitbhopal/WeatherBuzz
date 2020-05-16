package com.intern.weatherbuzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);

                startActivity(i);


                finish();

            }

        }, 3000);
    }
}
