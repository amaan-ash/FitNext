package com.example.fitnext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {
    private Button button2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        auth= FirebaseAuth.getInstance();
        button2=findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                finish();
                startActivity(new Intent(DashBoard.this,MainActivity.class));
                Toast.makeText(DashBoard.this,"Logout Successful", Toast.LENGTH_SHORT).show();
            }
        });
        //the below is the replacement of the onBackPressed() method
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        };

        // Registering the callback
        getOnBackPressedDispatcher().addCallback(this, callback);

        //rest code of the onCreate() method

    }


    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to exit the app?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks "Yes", exit the app
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicks "No", do nothing and close the dialog
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.alert)
                .show();
    }
}

