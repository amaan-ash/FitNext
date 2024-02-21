package com.example.fitnext;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DietPlanFragment extends Fragment {
    private Button muscleGainBtn;
    private Button weightGainBtn;
    private Button weightLossBtn;
    private Button diabetesPlanBtn;

    public DietPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_diet_plan, container, false);

        //getting views
        muscleGainBtn=view.findViewById(R.id.muscleGainBtn);
        weightGainBtn=view.findViewById(R.id.weightGainBtn);
        weightLossBtn=view.findViewById(R.id.weightLossBtn);
        diabetesPlanBtn=view.findViewById(R.id.diabetesPlanBtn);

     muscleGainBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.fragment_container,new MuscleGainFragment());
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
     });

     weightGainBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.fragment_container,new WeightGainFragment());
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
     });

     weightLossBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.fragment_container,new WeightLossFragment());
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
     });


     diabetesPlanBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.fragment_container,new DiabetesPlanFragment());
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
     });


        return view;
    }
}