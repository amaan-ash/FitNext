package com.example.fitnext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.fragment.app.Fragment;

public class PedometerFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private boolean running = false;
    private float totalSteps = 0f;
    private float previousTotalSteps = 0f;
    private static final int REQUEST_CODE = 123;
    private Button resetBtn;
    private TextView tvStepsTaken;
    private ImageView imageView;

    public PedometerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedometer, container, false);

        resetBtn = view.findViewById(R.id.resetBtn);
        tvStepsTaken = view.findViewById(R.id.tv_stepsTaken);
        imageView = view.findViewById(R.id.imgView);

        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, REQUEST_CODE);
        } else {
            // Permission is already granted, you can proceed with activity recognition
        }

        // Initialize sensorManager
        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        // Load data method
        loadData();

        // Set up the reset button here
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only reset if the user confirms
                new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Reset Steps")
                        .setMessage("Are you sure you want to reset your step count to zero?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                previousTotalSteps = totalSteps;
                                tvStepsTaken.setText("0");
                                saveData();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        // Adding listener to the image
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Click on the below button to reset steps", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;

        // Obtain the step counter sensor
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            // Display a toast message if the sensor is not available
            Toast.makeText(requireContext(), "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            // Register a sensor listener with SENSOR_DELAY_UI
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Handle sensor events
        TextView tvStepsTaken = requireView().findViewById(R.id.tv_stepsTaken);

        if (running) {
            totalSteps = event.values[0];

            // Calculate current steps
            int currentSteps = (int) (totalSteps - previousTotalSteps);

            // Update the TextView
            tvStepsTaken.setText(String.valueOf(currentSteps));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // This method is not used in this example
    }

    private void saveData() {
        // Save data using SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private void loadData() {
        // Load data using SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("key1", 0f);

        // Log the loaded data (for debugging)
        android.util.Log.d("MainActivity", String.valueOf(savedNumber));

        previousTotalSteps = savedNumber;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                // You can now start using activity recognition
                Toast.makeText(requireContext(), "Thank you for granting permission", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Without granting permission, you can't access the pedometer", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
