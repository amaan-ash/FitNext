package com.example.fitnext;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class amazonFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public amazonFragment() {
        // Required empty public constructor
    }



    public static amazonFragment newInstance(String param1, String param2) {
        amazonFragment fragment = new amazonFragment();
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
        View view= inflater.inflate(R.layout.fragment_amazon, container, false);
        Button option1,option2,option3,option4;

        option1=view.findViewById(R.id.option1);
        option2=view.findViewById(R.id.option2);
        option3=view.findViewById(R.id.option3);
        option4=view.findViewById(R.id.option4);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amazonUrl="https://www.amazon.in/s?k=whey+protein&crid=2Z8RBK42KSOJY&sprefix=whey+protein%2Caps%2C246&ref=nb_sb_noss_1";
               openWebViewFragment(amazonUrl);
            }

        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amazonUrl = "https://www.amazon.in/s?k=creatine+monohydrate&crid=JC69GKG7T7ZX&sprefix=cre%2Caps%2C190&ref=nb_sb_ss_ts-doa-p_1_3";
               openWebViewFragment(amazonUrl);
            }

        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String amazonUrl = "https://www.amazon.in/s?k=vitamins&crid=31B7IYHEIW32P&sprefix=vitamins%2Caps%2C198&ref=nb_sb_noss_1";
            openWebViewFragment(amazonUrl);

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        String amazonUrl = "https://www.amazon.in/s?k=fish+oil+capsules&crid=3R9OJERWRBOD1&sprefix=fish+%2Caps%2C208&ref=nb_sb_ss_ts-doa-p_1_5";
        openWebViewFragment(amazonUrl);

            }
        });
        return view;
    }

    private void openWebViewFragment(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, webViewFragment);
        transaction.addToBackStack(null); // Optional, to allow back navigation
        transaction.commit();
    }
}