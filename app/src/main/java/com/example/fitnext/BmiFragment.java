package com.example.fitnext;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BmiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BmiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputEditText textInputHeight;
    TextInputEditText textInputWeight;
    TextInputEditText textInputAge;
    Button buttonCalculate;
    TextView textViewResult;
    public BmiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BmiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BmiFragment newInstance(String param1, String param2) {
        BmiFragment fragment = new BmiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bmi, container, false);

        textInputAge=view.findViewById(R.id.textInputAge);
        textInputWeight=view.findViewById(R.id.textInputWeight);
        textInputHeight=view.findViewById(R.id.textInputHeight);
        textViewResult=view.findViewById(R.id.textViewResult);
        buttonCalculate=view.findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String weightStr = textInputWeight.getText().toString();
                String heightStr = textInputHeight.getText().toString();
                String ageStr = textInputAge.getText().toString();

                if (!weightStr.isEmpty() && !heightStr.isEmpty() && !ageStr.isEmpty()) {
                    // Convert input to numbers
                    double weight = Double.parseDouble(weightStr);
                    double height = Double.parseDouble(heightStr);
                    int age = Integer.parseInt(ageStr);

                    // Calculate BMI
                    double bmi = weight / (height * height);

                    // Determine health status
                    String healthStatus;
                    if (age < 2) {
                        healthStatus = "BMI categories are not applicable for individuals under 2.";
                    } else if (age < 18) {
                        healthStatus = getChildrenHealthStatus(bmi);
                    } else {
                        healthStatus = getAdultsHealthStatus(bmi);
                    }

                    // Display the result
                    textViewResult.setText("Your BMI is: " + String.format("%.2f", bmi) + "\n" + healthStatus);
                } else {
                    textViewResult.setText("Please enter weight, height, and age.");
                }
            }
        });
        return view;
    }
    private String getChildrenHealthStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight for age";
        } else if (bmi < 24.9) {
            return "Normal weight for age";
        } else if (bmi < 29.9) {
            return "Overweight for age";
        } else {
            return "Obese for age";
        }
    }

    private String getAdultsHealthStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Healthy weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else if (bmi < 34.9) {
            return "Obese (Class 1)";
        } else if (bmi < 39.9) {
            return "Obese (Class 2)";
        } else {
            return "Extreme Obese (Class 3)";
        }
    }
}