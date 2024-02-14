package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button loginBtnMain;
    private Button registerBtnMain;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            //to make the splash screen visible for 3 seconds
            Thread.sleep(2000);
        } catch (InterruptedException e) {
         throw new RuntimeException();
        }

        //to display the splash screen
        SplashScreen.installSplashScreen(MainActivity.this);

        setContentView(R.layout.activity_main);

        //to disable the dark mode in the app, the below line is used
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //rest of the code below
        loginBtnMain=findViewById(R.id.loginBtnMain);
        registerBtnMain=findViewById(R.id.registerBtnMain);

        //first get the object of the FireBaseAuth Class
        auth = FirebaseAuth.getInstance();

        //get the current user
        FirebaseUser user = auth.getCurrentUser();

        //if the user is already logged in then destroy this activity and go to dashboard screen
        if(user != null){
            finishAffinity();
            startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
        }

        //setting the login button
        loginBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //setting the register button

        registerBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}