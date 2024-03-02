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

public class adhdResultFragment extends Fragment {

    private TextView mResult;
    private Button mRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_adhd_result, container, false);

        mResult = view.findViewById(R.id.results);
        mRetry = view.findViewById(R.id.redo);

        Bundle b = getArguments();
        if (b != null) {
            int points = b.getInt("points");

            if (points >= 0 && points < 4) {
                mResult.setText("Your symptoms are not suggestive of ADHD");
            } else if (points >= 4) {
                mResult.setText("Your symptoms are suggestive of ADHD");
            }
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adhdFragment resultFragment = new adhdFragment();
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
