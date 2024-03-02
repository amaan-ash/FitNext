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

public class OcdResultFragment extends Fragment {

    private TextView mResult;
    private Button mRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ocd_result, container, false);

        mResult = view.findViewById(R.id.results);
        mRetry = view.findViewById(R.id.redo);

        Bundle b = getArguments();
        if (b != null) {
            int points = b.getInt("points");

            if (points >= 0 && points < 21) {
                mResult.setText("Your symptoms are not suggestive of OCD");
            } else if (points >= 21) {
                mResult.setText("Your symptoms are suggestive of OCD");
            }
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OcdFragment resultFragment = new OcdFragment();
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
