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
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    String email;
    String password;
    FirebaseAuth auth;

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
        progressBar.setVisibility(View.GONE);
        //rest of the code below
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();



        //the below code is used to prevent entering of the white space
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

        textInputPasswordLogin.setFilters(new InputFilter[]{noWhiteSpaceFilter});



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
                resetPassword();
            }
        });

        //setting the login Button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is called for the login purpose
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


        if(TextUtils.isEmpty(email)){
            textLayoutEmailLogin.setError("email is required");
            textLayoutEmailLogin.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textLayoutEmailLogin.setError("not a valid email address");
            textLayoutEmailLogin.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)){
            textLayoutPasswordLogin.setError("password is required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }

        if(password.length() < 8){
            textLayoutPasswordLogin.setError("minimum 8 characters required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }

        //the below method is invoked to make the user logged in
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            validate(email, password);
        }




    }

    public void validate(String userEmail, String userPassword){

       progressBar.setVisibility(View.VISIBLE);

        //using the firebase code to sign in with email and password
        auth.signInWithEmailAndPassword(userEmail,
                        userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            if(auth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(LoginActivity.this, DashBoard.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Please verify your email address"
                                        , Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage()
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //items selected on the app bar handled in this method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle the Up arrow button of the app bar
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;

            // Other menu item cases can be added here if needed

        }

        return super.onOptionsItemSelected(item);
    }


    //the below method is used for validating the email
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            textLayoutEmailLogin.setError("email is required");
            textLayoutEmailLogin.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLayoutEmailLogin.setError("not a valid email address");
                textLayoutEmailLogin.requestFocus();
                return;
            }


        // Clear the error if the email is valid
        textLayoutEmailLogin.setError(null);
    }


    //the below method is used for validating the password
    private void validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            textLayoutPasswordLogin.setError("password is required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }
         if (password.length() < 8) {
            textLayoutPasswordLogin.setError("minimum 8 characters required");
            textLayoutPasswordLogin.requestFocus();
            return;
        }


            textLayoutPasswordLogin.setError(null);
        }


        //the below code is used for resetting the password
    public void resetPassword(){
        final String resetEmail = textInputEmailLogin.getText().toString();

        if (resetEmail.isEmpty()) {
            textLayoutEmailLogin.setError("enter email to reset password");
            textLayoutEmailLogin.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //using the firebase code to send email for resetting the password
        auth.sendPasswordResetEmail(resetEmail)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "An email has been sent to reset password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    }

