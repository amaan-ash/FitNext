package com.example.fitnext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Map;

public class MyGoals_Fragment extends Fragment {

    private EditText etGoal;
    private LinearLayout layoutGoals;
    private ArrayList<CheckBox> checkBoxList;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_goals_, container, false);

        etGoal = rootView.findViewById(R.id.etGoal);
        Button btnAddGoal = rootView.findViewById(R.id.btnAddGoal);
        Button btnPls = rootView.findViewById(R.id.btnpls);

        layoutGoals = rootView.findViewById(R.id.layoutGoals);
        checkBoxList = new ArrayList<>();
        sharedPreferences = requireContext().getSharedPreferences("MyGoals", Context.MODE_PRIVATE);

        etGoal.setVisibility(View.GONE);
        btnAddGoal.setVisibility(View.GONE);

        btnPls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etGoal.setVisibility(View.VISIBLE);
                btnAddGoal.setVisibility(View.VISIBLE);
            }
        });

        btnAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etGoal.getVisibility() == View.VISIBLE) {
                    String goalText = etGoal.getText().toString().trim();
                    if (!goalText.isEmpty()) {
                        addGoal(goalText);
                        etGoal.setText(""); // Clear the EditText after adding the goal
                        saveGoals(); // Save goals after adding
                    }
                    etGoal.setVisibility(View.GONE); // Hide EditText after adding goal
                } else {
                    etGoal.setVisibility(View.VISIBLE); // Show EditText
                }
            }
        });

        loadGoals();

        return rootView;
    }

    private void addGoal(final String goalText) {
        // Create a new CheckBox for the goal
        final CheckBox checkBox = new CheckBox(requireContext());
        checkBox.setText(goalText);
        checkBox.setTextSize(25);

        // Set layout parameters to add space between CheckBoxes
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, 20); // Add top margin for space between CheckBoxes
        checkBox.setLayoutParams(layoutParams);

        // Add CheckBox to the LinearLayout
        layoutGoals.addView(checkBox);
        checkBoxList.add(checkBox);

        // Set a listener to remove the goal when the CheckBox is clicked
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    showConfirmationDialog(checkBox);
                } else if (!checkBox.isChecked()) {
                    checkBox.setChecked(false);
                }
            }
        });
    }

    private void showConfirmationDialog(final CheckBox checkBox) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireContext());
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder.setMessage("Are you sure you want to remove this goal?");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Remove the checkbox if the user clicks OK
                layoutGoals.removeView(checkBox);
                checkBoxList.remove(checkBox);
                saveGoals(); // Save goals after removal
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing if the user clicks Cancel
                dialogInterface.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void saveGoals() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clear existing goals
        for (CheckBox checkBox : checkBoxList) {
            editor.putBoolean(checkBox.getText().toString(), checkBox.isChecked());
        }
        editor.apply();
    }

    private void loadGoals() {
        Map<String, ?> savedGoals = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : savedGoals.entrySet()) {
            String goalText = entry.getKey();
            boolean isChecked = (Boolean) entry.getValue();
            addGoalWithState(goalText, isChecked);
        }
    }

    private void addGoalWithState(final String goalText, boolean isChecked) {
        // Create a new CheckBox for the goal
        final CheckBox checkBox = new CheckBox(requireContext());
        checkBox.setText(goalText);
        checkBox.setChecked(isChecked);
        checkBox.setTextSize(25);

        // Set layout parameters to add space between CheckBoxes
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, 20); // Add top margin for space between CheckBoxes
        checkBox.setLayoutParams(layoutParams);

        // Add CheckBox to the LinearLayout
        layoutGoals.addView(checkBox);
        checkBoxList.add(checkBox);

        // Set a listener to remove the goal when the CheckBox is clicked
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    showConfirmationDialog(checkBox);
                } else if (!checkBox.isChecked()) {
                    checkBox.setChecked(false);
                }
            }
        });
    }
}
