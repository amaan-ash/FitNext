package com.example.fitnext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PtsdFragment extends Fragment {

    private Button yesButton;
    private Button noButton;
    private int score;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ptsd_1, container, false);

        yesButton = view.findViewById(R.id.yes);
        noButton = view.findViewById(R.id.no);
        score = 0;

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new PtsdActivityFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new PtsdResultFragment());
                Bundle b = new Bundle();
                b.putInt("score", score);
                PtsdResultFragment fragment = new PtsdResultFragment();
                fragment.setArguments(b);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
