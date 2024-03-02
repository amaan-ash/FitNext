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

public class anxietyFragment extends Fragment {

    private anxietyQuestionModel mQues = new anxietyQuestionModel();
    private TextView mQuesView;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;
    private int mPoint = 0;
    private int mQuesNumber = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_anxiety, container, false);

        mQuesView = view.findViewById(R.id.question);
        mChoice1 = view.findViewById(R.id.choice1);
        mChoice2 = view.findViewById(R.id.choice2);
        mChoice3 = view.findViewById(R.id.choice3);
        mChoice4 = view.findViewById(R.id.choice4);

        updateQuestion();

        mChoice1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mQuesNumber == mQues.getLength()) {
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        mChoice2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPoint = mPoint + 1;
                if (mQuesNumber == mQues.getLength()) {
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        mChoice3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPoint = mPoint + 2;
                if (mQuesNumber == mQues.getLength()) {
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        mChoice4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPoint = mPoint + 3;
                if (mQuesNumber == mQues.getLength()) {
                    updateResult();
                } else {
                    updateQuestion();
                }
            }
        });

        return view;
    }

    private void updateQuestion() {
        mQuesView.setText(mQues.getQuestion(mQuesNumber));
        mChoice1.setText(mQues.getChoice1(mQuesNumber));
        mChoice2.setText(mQues.getChoice2(mQuesNumber));
        mChoice3.setText(mQues.getChoice3(mQuesNumber));
        mChoice4.setText(mQues.getChoice4(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult() {
        anxietyResultFragment resultFragment = new anxietyResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("points", mPoint);
        resultFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)  // Optionally add the transaction to back stack
                .commit();
    }

}
