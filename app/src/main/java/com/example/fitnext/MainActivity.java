package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button loginBtnMain;
    private Button registerBtnMain;

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

        //rest of the code below
        loginBtnMain=findViewById(R.id.loginBtnMain);
        registerBtnMain=findViewById(R.id.registerBtnMain);

        loginBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        registerBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}