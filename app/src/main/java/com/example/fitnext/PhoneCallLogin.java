package com.example.fitnext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PhoneCallLogin extends AppCompatActivity {
    TextInputEditText phoneNumberInput;
    TextInputLayout phoneNumberLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_login);

        //getting views
        phoneNumberInput=findViewById(R.id.phoneNumberInput);
        phoneNumberLayout=findViewById(R.id.phoneNumberLayout);

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

    }
}