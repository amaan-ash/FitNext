package com.example.fitnext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HelpLine extends Fragment {
    private static final int REQUEST_CALL = 1;

    private Button kidsHelpCall;
    private Button safeHavenCall;
    private Button translifelinecall;
    private Button suicideHelpCall;
    private Button ambulanceCall;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission granted, make the call
                    Toast.makeText(requireContext(), "Permission granted. Click again to make the call.", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission denied, check if "Don't ask again" is checked
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CALL_PHONE)) {
                        // User selected "Don't ask again", show guidance to app settings
                        Toast.makeText(requireContext(), "Please enable phone permission.", Toast.LENGTH_SHORT).show();
                        openAppSettings();
                    } else {
                        // Permission denied, but can be requested again
                        Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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

        kidsHelpCall.setOnClickListener(v -> call("tel:1098"));

        safeHavenCall.setOnClickListener(v -> call("tel:1091"));

        translifelinecall.setOnClickListener(v -> call("tel:1097"));

        suicideHelpCall.setOnClickListener(v -> call("tel:9152987821"));

        ambulanceCall.setOnClickListener(v -> call("tel:108"));

        return view;
    }

    public void call(String phone) {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
        }
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
