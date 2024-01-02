package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        //rest of the code

        //setting the login button
        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
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

    }

    //this method is invoked when the user clicks on Register Here button
    private void registerUser() {
        String email=textInputEmailRegister.getText().toString().trim();
        String password=textInputPasswordRegister.getText().toString().trim();
        String confirmPassword=textConfirmPasswordRegister.getText().toString().trim();


        if(email.isEmpty()){
          textLayoutEmailRegister.setError("Email");
           textLayoutEmailRegister.requestFocus();

            textInputEmailRegister.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    textLayoutEmailRegister.setError(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            return;
        }
        else{
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLayoutEmailRegister.setError("Not a valid email Address");
                textLayoutEmailRegister.requestFocus();
                return;
            }
            else{
                textLayoutEmailRegister.setError(null);
            }
        }
        if(password.isEmpty()){
            textLayoutPasswordRegister.setError("Choose Password");
            textLayoutPasswordRegister.requestFocus();

            textInputPasswordRegister.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    textLayoutPasswordRegister.setError(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            return;
        }
        if(confirmPassword.isEmpty()){
            textLayoutConfirmPasswordRegister.setError("Confirm Your Password");
            textLayoutConfirmPasswordRegister.requestFocus();

            textConfirmPasswordRegister.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     textLayoutConfirmPasswordRegister.setError(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            return;
        }


        if (password.length() < 8) {
            textLayoutPasswordRegister.setError("Minimum 8 characters");
            textLayoutPasswordRegister.requestFocus();
            return;
        }
        else {
            textLayoutPasswordRegister.setError(null);
        }

        if(!password.equals(confirmPassword)){
            textLayoutConfirmPasswordRegister.setError("Passwords Do not Match");
            textLayoutConfirmPasswordRegister.requestFocus();
            return;
        }
        else{
            textLayoutConfirmPasswordRegister.setError(null);
        }


    }

}