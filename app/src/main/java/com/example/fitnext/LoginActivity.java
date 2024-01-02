package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText textInputEmailLogin;
    private TextInputEditText textInputPasswordLogin;
    private TextView newUserTextview;
    private TextView forgotPasswordTextview;
    private Button loginBtn;
    private ImageView googleBtn;
    private ImageView callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting views
        textInputEmailLogin=findViewById(R.id.textInputEmailLogin);
        textInputPasswordLogin=findViewById(R.id.textInputPasswordLogin);
        newUserTextview=findViewById(R.id.newUserTextview);
        forgotPasswordTextview=findViewById(R.id.forgotPasswordTextview);
        loginBtn=findViewById(R.id.loginBtn);
        googleBtn=findViewById(R.id.googleBtn);
        callBtn=findViewById(R.id.callBtn);


        //rest of the code below

        //setting the newUser Textview
        newUserTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //setting the forgotPassword Textview
        forgotPasswordTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //setting the login Button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intent);
                finish();
            }
        });

        //setting the google SignIn Button
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //setting the call SignIn Button
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}