package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(3000);
            SplashScreen.installSplashScreen(MainActivity.this);
        } catch (InterruptedException e) {
         throw new RuntimeException();
        }

        setContentView(R.layout.activity_main);
    }
}