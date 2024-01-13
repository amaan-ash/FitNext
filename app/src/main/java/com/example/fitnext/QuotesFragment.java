package com.example.fitnext;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] motivationalQuotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
            "The only way to do great work is to love what you do. - Steve Jobs",

            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",

            "Believe you can and you're halfway there. - Theodore Roosevelt",

            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",

            "Your time is limited, don't waste it living someone else's life. - Steve Jobs",

            "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",

            "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",

            "Strive not to be a success, but rather to be of value. - Albert Einstein",

            "Success is stumbling from failure to failure with no loss of enthusiasm. - Winston S. Churchill",

            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",

    };

    public QuotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuotesFragment newInstance(String param1, String param2) {
        QuotesFragment fragment = new QuotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        TextView textMotivationalQuote = view.findViewById(R.id.textMotivationalQuote);

        // Get a random quote
        String randomQuote = getRandomQuote();
        textMotivationalQuote.setText(randomQuote);

        return view;
    }
    private String getRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(motivationalQuotes.length);
        return motivationalQuotes[index];
    }
}