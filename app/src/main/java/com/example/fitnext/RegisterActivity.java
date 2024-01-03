package com.example.fitnext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

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

    String email;
    String password;
    String confirmPassword;

    FirebaseAuth auth;
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

        //getting the instance of the FireBaseAuth class
        auth = FirebaseAuth.getInstance();

        //rest of the code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressBar.setVisibility(View.GONE);

        //for preventing the user from entering the white space
        InputFilter noWhiteSpaceFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Check each character being entered
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        // If a blank space is detected, prevent it from being entered
                        return "";
                    }
                }
                // Allow other characters
                return null;
            }
        };

        textInputPasswordRegister.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        textConfirmPasswordRegister.setFilters(new InputFilter[]{noWhiteSpaceFilter});



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

            //remove the error
            textLayoutConfirmPasswordRegister.setError(null);

    }

    //this method is for validating the entered password
    private void validateEnterPassword(String password) {
        // Check if the password is empty
        if (TextUtils.isEmpty(password)) {
            textLayoutPasswordRegister.setError("Password is required");
            textLayoutPasswordRegister.requestFocus();
            return;
        }

        // Check if the password is less than 8 characters
        if (password.length() < 8) {
            textLayoutPasswordRegister.setError("Minimum 8 characters required");
            textLayoutPasswordRegister.requestFocus();
            return;
        }

        // Check if the password contains at least one uppercase letter
        if (!containsUppercase(password)) {
            textLayoutPasswordRegister.setError("Password must contain at least one uppercase letter");
            textLayoutPasswordRegister.requestFocus();
            return;
        }

        // Check if the password contains at least one number
        if (!containsNumber(password)) {
            textLayoutPasswordRegister.setError("Password must contain at least one number");
            textLayoutPasswordRegister.requestFocus();
            return;
        }

        // Check if the password contains only allowed special symbols
        if (!containsOnlyAllowedSymbols(password)) {
            textLayoutPasswordRegister.setError("Password can only contain '@' symbol");
            textLayoutPasswordRegister.requestFocus();
            return;
        }

        // Clear the error if the password is valid
        textLayoutPasswordRegister.setError(null);
    }

    // Helper method to check if the password contains at least one uppercase letter
    private boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if the password contains at least one number
    private boolean containsNumber(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if the password contains only allowed special symbols
    private boolean containsOnlyAllowedSymbols(String password) {
        // Define allowed special symbols
        String allowedSymbols = "@";

        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && allowedSymbols.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }



    //  this method for email validation
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            textLayoutEmailRegister.setError("email is required");
            textLayoutEmailRegister.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLayoutEmailRegister.setError("not a valid email address");
                textLayoutEmailRegister.requestFocus();
                return;
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

        //the below code is used if all the inputs are given correctly by the user


        Toast.makeText(this, "Registration in Progress", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);



        //to register the user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            final User user = new User(
                                    email,
                                    password

                            );

                            //important to retrieve data and send data based on user email
                            FirebaseUser userNameInFirebase = auth.getCurrentUser();
                            String UserID=userNameInFirebase.getEmail();
                            String resultEmail = UserID.replace(".","");

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(resultEmail).child("UserDetails")
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                Log.d("final","the user is registered");

                                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                finish();

                                            }

                                            else {
                                                Log.d("registration failed","task is successful but registration is failed");
                                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }

                        else {
                            Log.d("task no successful","the task is not successful");
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });












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