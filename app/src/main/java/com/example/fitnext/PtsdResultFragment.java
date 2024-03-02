package com.example.fitnext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PtsdResultFragment extends Fragment {

    private TextView mResults;
    private Button mRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ptsd_results_2, container, false);

        mResults = view.findViewById(R.id.resultP);
        mRetry = view.findViewById(R.id.retryP);

        Bundle b = getArguments();
        if (b != null) {
            int score = b.getInt("score");

            if (score >= 3) {
                mResults.setText("You are likely to be experiencing PTSD");
            } else {
                mResults.setText("You are not likely to be experiencing PTSD");
            }
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PtsdActivityFragment resultFragment = new PtsdActivityFragment();
                Bundle bundle = new Bundle();
                resultFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, resultFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
