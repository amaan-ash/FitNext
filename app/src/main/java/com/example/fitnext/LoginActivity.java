package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText textInputEmailLogin;
    private TextInputEditText textInputPasswordLogin;
    private TextInputLayout textLayoutEmailLogin;
    private TextInputLayout textLayoutPasswordLogin;
    private TextView newUserTextview;
    private TextView forgotPasswordTextview;
    private Button loginBtn;
    private ImageView googleBtn;
    private ImageView callBtn;
    ProgressBar progressBar;

    String email="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting views
        textLayoutEmailLogin=findViewById(R.id.textLayoutEmailLogin);
        textLayoutPasswordLogin=findViewById(R.id.textLayoutPasswordLogin);
        textInputEmailLogin=findViewById(R.id.textInputEmailLogin);
        textInputPasswordLogin=findViewById(R.id.textInputPasswordLogin);
        newUserTextview=findViewById(R.id.newUserTextview);
        forgotPasswordTextview=findViewById(R.id.forgotPasswordTextview);
        loginBtn=findViewById(R.id.loginBtn);
        googleBtn=findViewById(R.id.googleBtn);
        callBtn=findViewById(R.id.callBtn);
        progressBar=findViewById(R.id.progressBar);

        //rest of the code below
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar.setVisibility(View.GONE);



        //TextWatcher implementation for Email Field
        textInputEmailLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        //TextWatcher implementation for Password Field
        textInputPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validatePassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the password contains any blank spaces
                if (editable.toString().contains(" ")) {
                    // Password contains blank spaces, show an error message
                    textLayoutPasswordLogin.setError("password cannot contain blank spaces");
                } else {
                    // Password is valid, clear any previous error
                    textLayoutPasswordLogin.setError(null);
                }
            }

        });
        //setting the newUser Textview
        newUserTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
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
                loginUser();

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

    private void loginUser() {
        email = textInputEmailLogin.getText().toString().trim();
        password = textInputPasswordLogin.getText().toString().trim();


        if(email.isEmpty()){
            textLayoutEmailLogin.setError("email is required");
            textLayoutEmailLogin.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textLayoutEmailLogin.setError("not a valid email address");
            textLayoutEmailLogin.requestFocus();
            return;
        }

        else if(password.isEmpty()){
            textLayoutPasswordLogin.setError("password is required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }
        else if(password.contains(" ")){
            textLayoutPasswordLogin.setError("password cannot contain blank spaces");
            textLayoutPasswordLogin.requestFocus();
            return;
        }

        else if(password.length() < 8){
            textLayoutPasswordLogin.setError("minimum 8 characters required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }

        else{
            Toast.makeText(this, "shit", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "testing", Toast.LENGTH_SHORT).show();

    }


    //items selected on the app bar handled in this method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle the Up button press
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;

            // Other menu item cases can be added here if needed

        }

        return super.onOptionsItemSelected(item);
    }


    //the below method is used for validating the email
    private void validateEmail(String email) {
        if (email.isEmpty()) {
            textLayoutEmailLogin.setError("email is required");
            textLayoutEmailLogin.requestFocus();
            return;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLayoutEmailLogin.setError("not a valid email address");
                textLayoutEmailLogin.requestFocus();
                return;
            }
        }

        // Clear the error if the email is valid
        textLayoutEmailLogin.setError(null);
    }


    //the below method is used for validating the password
    private void validatePassword(String password) {
        if (password.length() < 1) {
            textLayoutPasswordLogin.setError("password is required");
            textLayoutPasswordLogin.requestFocus();
            return;
        } else if (password.length() < 8) {
            textLayoutPasswordLogin.setError("minimum 8 characters required");
            textLayoutPasswordLogin.requestFocus();
            return;

        }
        //clear the error if the password is valid
        textLayoutPasswordLogin.setError(null);
    }
}