package com.example.fitnext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout textLayoutPasswordRegister;
    private TextInputLayout textLayoutEmailRegister;
    private TextInputLayout textLayoutConfirmPasswordRegister;
    private TextInputEditText textInputEmailRegister;
    private TextInputEditText textInputPasswordRegister;
    private TextInputEditText textConfirmPasswordRegister;
    private TextView goToSigninTextview;
    private Button registerBtn;
    private Button goToLoginBtn;
    ProgressBar progressBar;

    String email="";
    String password="";
    String confirmPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getting views
        textLayoutPasswordRegister=findViewById(R.id.textLayoutPasswordRegister);
        textLayoutEmailRegister=findViewById(R.id.textLayoutEmailRegister);
        textLayoutConfirmPasswordRegister=findViewById(R.id.textLayoutConfirmPasswordRegister);
        textInputEmailRegister=findViewById(R.id.textInputEmailRegister);
        textConfirmPasswordRegister=findViewById(R.id.textConfirmPasswordRegister);
        textInputPasswordRegister=findViewById(R.id.textInputPasswordRegister);
        goToSigninTextview=findViewById(R.id.goToSigninTextview);
        registerBtn=findViewById(R.id.registerBtn);
        goToLoginBtn=findViewById(R.id.goToLoginBtn);
        progressBar=findViewById(R.id.progressBar);

        //rest of the code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressBar.setVisibility(View.GONE);

        //setting the login button
        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //setting the sign in textview
        goToSigninTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        //setting the register button
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               registerUser();
            }
        });

        //TextWatcher implementation for Email Input Field
        textInputEmailRegister.addTextChangedListener(new TextWatcher() {
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


        //TextWatcher implementation for Enter Password Field
        textInputPasswordRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEnterPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Check if the password contains any blank spaces
                if (editable.toString().contains(" ")) {
                    // Password contains blank spaces, show an error message
                    textLayoutPasswordRegister.setError("password cannot contain blank spaces");
                    textLayoutPasswordRegister.requestFocus();
                    return;
                }

            }
        });


        //TextWatcher implementation for confirm password field
        textConfirmPasswordRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateConfirmPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Check if the password contains any blank spaces
                if (editable.toString().contains(" ")) {
                    // Password contains blank spaces, show an error message
                    textLayoutConfirmPasswordRegister.setError("password cannot contain blank spaces");
                    textLayoutConfirmPasswordRegister.requestFocus();
                    return;
                }
            }
        });

    }

    private void validateConfirmPassword(String confirmPassword) {
        if (TextUtils.isEmpty(confirmPassword)) {
            textLayoutConfirmPasswordRegister.setError("confirm your password");
            textLayoutConfirmPasswordRegister.requestFocus();
            return;
        }

        String password=textInputPasswordRegister.getText().toString().trim();

        if (!password.equals(confirmPassword)) {
            textLayoutConfirmPasswordRegister.setError("passwords do not match");
            textLayoutConfirmPasswordRegister.requestFocus();
            return;
        }

        if (confirmPassword.contains(" ")) {
            // Password contains blank spaces, show an error message
            textLayoutPasswordRegister.setError("password cannot contain blank spaces");
            textLayoutPasswordRegister.requestFocus();
            return;
        }
            textLayoutConfirmPasswordRegister.setError(null);

    }

    //this method is for validating the entered password
    private void validateEnterPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            textLayoutPasswordRegister.setError("password is required");
            textLayoutPasswordRegister.requestFocus();
            return;
        }
        else if (password.length() < 8) {
            textLayoutPasswordRegister.setError("minimum 8 characters required");
            textLayoutPasswordRegister.requestFocus();
            return;

        }
        // Check if the password contains any blank spaces
        else if (password.contains(" ")) {
            // Password contains blank spaces, show an error message
            textLayoutPasswordRegister.setError("password cannot contain blank spaces");
            textLayoutPasswordRegister.requestFocus();
            return;
        }
        //clear the error if the password is valid
        textLayoutPasswordRegister.setError(null);
    }

    //  this method for email validation
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            textLayoutEmailRegister.setError("email is required");
            textLayoutEmailRegister.requestFocus();
            return;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLayoutEmailRegister.setError("not a valid email address");
                textLayoutEmailRegister.requestFocus();
                return;
            }
        }

        // Clear the error if the email is valid
        textLayoutEmailRegister.setError(null);
    }

    //this method is invoked when the user clicks on Register Here button
    private void registerUser() {
         email = textInputEmailRegister.getText().toString().trim();
         password = textInputPasswordRegister.getText().toString().trim();
         confirmPassword = textConfirmPasswordRegister.getText().toString().trim();

      if(TextUtils.isEmpty(email)){
          textLayoutEmailRegister.setError("email is required");
          textLayoutEmailRegister.requestFocus();
          return;
      }

      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
          textLayoutEmailRegister.setError("not a valid email address");
          textLayoutEmailRegister.requestFocus();
          return;
      }

       if(TextUtils.isEmpty(password)){
          textLayoutPasswordRegister.setError("please enter password");
          textLayoutPasswordRegister.requestFocus();
          return;
      }

       if(password.contains(" ")){
          textLayoutPasswordRegister.setError("password cannot contain blank spaces");
          textLayoutPasswordRegister.requestFocus();
          return;
      }

       if(password.length() < 8){
          textLayoutPasswordRegister.setError("minimum 8 characters required");
          textLayoutPasswordRegister.requestFocus();
          return;
      }

        if(TextUtils.isEmpty(confirmPassword)){
           textLayoutConfirmPasswordRegister.setError("confirm your password");
           textLayoutConfirmPasswordRegister.requestFocus();
           return;
       }

        if (!confirmPassword.equals(password)) {
            textLayoutConfirmPasswordRegister.setError("Passwords do not match");
            textLayoutConfirmPasswordRegister.requestFocus();
            return;
        }

        else{
          Toast.makeText(this, "registration is ongoing", Toast.LENGTH_SHORT).show();
      }










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

}