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

public class PtsdActivityFragment extends Fragment {

    private ptsdQuestionModel mQues = new ptsdQuestionModel();
    private TextView mQuesView;
    private int mQuesNum = 0;
    private int mScore = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ptsd_2, container, false);

        mQuesView = view.findViewById(R.id.quesP);
        Button yesButton = view.findViewById(R.id.yesP);
        Button noButton = view.findViewById(R.id.noP);

        updateQuestion();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore = mScore + 1;
                if (mQuesNum == mQues.getLength()) {
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuesNum == mQues.getLength()){
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        return view;
    }

    private void updateQuestion(){
        mQuesView.setText(mQues.getQuestion(mQuesNum));
        mQuesNum++;
    }

    private void updateResult() {
        PtsdResultFragment resultFragment = new PtsdResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("score", mScore);
        resultFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)
                .commit();
    }

}
