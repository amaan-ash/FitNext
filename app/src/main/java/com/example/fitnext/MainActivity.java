package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            //to make the splash screen visible for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
         throw new RuntimeException();
        }

        //to display the splash screen
        SplashScreen.installSplashScreen(MainActivity.this);

        setContentView(R.layout.activity_main);

        //rest of the code


    }
}