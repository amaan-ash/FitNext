package com.example.fitnext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class QuizFragment extends Fragment {

    private Button adhd;
    private Button anxiety;
    private Button depression;
    private Button ocd;
    private Button ptsd;
    private Button bipolar;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quiz, container, false);

        linearLayout = rootView.findViewById(R.id.quiz_layout);
        linearLayout.getBackground().setAlpha(80);

        adhd = rootView.findViewById(R.id.button_adhd);
        adhd.getBackground().setAlpha(180);

        anxiety = rootView.findViewById(R.id.button_anxiety);
        anxiety.getBackground().setAlpha(180);

        depression = rootView.findViewById(R.id.button_depression);
        depression.getBackground().setAlpha(180);

        ocd = rootView.findViewById(R.id.button_ocd);
        ocd.getBackground().setAlpha(180);

        ptsd = rootView.findViewById(R.id.button_ptsd);
        ptsd.getBackground().setAlpha(180);

        bipolar = rootView.findViewById(R.id.button_bipolar);
        bipolar.getBackground().setAlpha(180);

        adhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new adhdFragment());
            }
        });

        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new anxietyFragment());
            }
        });

        bipolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new bipolarFragment());
            }
        });

        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new depressionFragment());
            }
        });

        ocd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new OcdFragment());
            }
        });

        ptsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new PtsdFragment());
            }
        });

        return rootView;
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
