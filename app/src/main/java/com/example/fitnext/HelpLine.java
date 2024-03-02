package com.example.fitnext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HelpLine extends Fragment {
    Button kidsHelpCall;
    Button safeHavenCall;
    Button translifelinecall;
    Button suicideHelpCall;
    Button ambulanceCall;
    private static final int REQUEST_CALL = 1;

    public HelpLine() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_line, container, false);

        kidsHelpCall = view.findViewById(R.id.kidsHelpCall);
        safeHavenCall = view.findViewById(R.id.safeHavenCall);
        translifelinecall = view.findViewById(R.id.transLifelineCall);
        suicideHelpCall = view.findViewById(R.id.suicideHelpCall);
        ambulanceCall = view.findViewById(R.id.ambulanceCall);

        kidsHelpCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("tel:1098");
            }
        });

        safeHavenCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("tel:1091");
            }
        });

        translifelinecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("tel:1097");
            }
        });

        suicideHelpCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("tel:9152987821");
            }
        });

        ambulanceCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call("tel:108");
            }
        });

        return view;
    }

    public void call(String phone) {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                Toast.makeText(requireContext(), "Permission granted. Click again to make the call.", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
