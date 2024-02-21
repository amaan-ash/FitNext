package com.example.fitnext;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerifyActivity extends AppCompatActivity {

    Button verifyCodeBtn;
 FirebaseAuth auth;
    String otpId,phoneNumber;

   PinView otpInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        verifyCodeBtn = findViewById(R.id.verifyCodeBtn);
        otpInput =findViewById(R.id.otpInput);
        phoneNumber = getIntent().getStringExtra("phoneNo");

        initiateOtp();

        verifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp=otpInput.getText().toString();
                if(TextUtils.isEmpty(otp)){
                    Toast.makeText(OtpVerifyActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(otp.length()<6){
                    Toast.makeText(OtpVerifyActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpId, otpInput.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
    }
    private void initiateOtp()
    {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpId =verificationId;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(OtpVerifyActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OtpVerifyActivity.this, DashBoardActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));


                        } else {
                            Toast.makeText(getApplicationContext(),"Incorrect OTP",Toast.LENGTH_LONG).show();
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
}