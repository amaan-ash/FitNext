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

public class anxietyResultFragment extends Fragment {

    private TextView mResult;
    private Button mRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_anxiety_results, container, false);

        mResult = view.findViewById(R.id.result);
        mRetry = view.findViewById(R.id.retry);

        Bundle b = getArguments();
        if (b != null) {
            int points = b.getInt("points");

            if (points >= 0 && points <= 9) {
                mResult.setText("The level of your anxiety is none to mild");
            } else if (points >= 10 && points <= 14) {
                mResult.setText("The level of your anxiety is moderate");
            } else if (points >= 15 && points <= 21) {
                mResult.setText("The level of your anxiety is severe");
            }
        }

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               anxietyFragment resultFragment = new anxietyFragment();
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
