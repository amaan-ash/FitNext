package com.example.fitnext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DepressionResultFragment extends Fragment {

    private TextView mResult;
    private Button mRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_depression_result, container, false);

        mResult = view.findViewById(R.id.results);
        mRetry = view.findViewById(R.id.redo);

        Bundle b = getArguments();
        if (b != null) {
            int points = b.getInt("points");

            if (points >= 0 && points <= 4) {
                mResult.setText("The level of your depression is None to Minimal");
            } else if (points >= 5 && points <= 9) {
                mResult.setText("The level of your depression is Mild");
            } else if (points >= 10 && points <= 14) {
                mResult.setText("The level of your depression is Moderate");
            } else if (points >= 15 && points <= 19) {
                mResult.setText("The level of your depression is Moderately severe");
            } else if (points >= 20 && points <= 27) {
                mResult.setText("The level of your depression is Severe");
            }
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depressionFragment resultFragment = new depressionFragment();
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
