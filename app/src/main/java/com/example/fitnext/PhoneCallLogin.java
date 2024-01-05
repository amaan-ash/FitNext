package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class PhoneCallLogin extends AppCompatActivity {
    TextInputEditText phoneNumberInput;
    TextInputLayout phoneNumberLayout;
    FloatingActionButton nextBtn;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_login);

        //getting views
        phoneNumberInput=findViewById(R.id.phoneNumberInput);
        phoneNumberLayout=findViewById(R.id.phoneNumberLayout);
        nextBtn=findViewById(R.id.nextBtn);
        countryCodePicker=findViewById(R.id.countryCodePicker);
        countryCodePicker.registerCarrierNumberEditText(phoneNumberInput);

        // Set an InputFilter to allow only digits
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Loop through the characters to allow only digits
                StringBuilder filteredStringBuilder = new StringBuilder();
                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);
                    if (Character.isDigit(currentChar)) {
                        filteredStringBuilder.append(currentChar);
                    }
                }
                return filteredStringBuilder.toString();
            }
        };

        phoneNumberInput.setFilters(new InputFilter[]{inputFilter});


        //rest of the code below

        //setting the next Button
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callVerifyOtp();
                    }
                });

    }

    private void callVerifyOtp() {
        if (TextUtils.isEmpty(phoneNumberInput.getText())) {
            phoneNumberLayout.setError("Enter Phone Number");
            phoneNumberLayout.requestFocus();
            return;
        }
        phoneNumberLayout.setError(null);
        Toast.makeText(this, "first reach", Toast.LENGTH_SHORT).show();

        String userEnteredNumber = phoneNumberLayout.getEditText().toString().trim();
        Toast.makeText(this, "reached", Toast.LENGTH_SHORT).show();
//        String phoneNumber="+"+countryCodePicker.getFullNumber()+userEnteredNumber;

        Toast.makeText(this, " second reached", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "third reach", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PhoneCallLogin.this, OtpVerify.class);
        intent.putExtra("phoneNo",countryCodePicker.getFullNumberWithPlus().replace(" ",""));
        Toast.makeText(this, "fourth reach", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}