
package com.example.fitnext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class flipkartFragment extends Fragment {

    public flipkartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flipkart, container, false);

        Button option1 = view.findViewById(R.id.option1);
        Button option2 = view.findViewById(R.id.option2);
        Button option3 = view.findViewById(R.id.option3);
        Button option4 = view.findViewById(R.id.option4);

        option1.setOnClickListener(v -> openWebViewFragment("https://www.flipkart.com/search?q=whey+protein"));
        option2.setOnClickListener(v -> openWebViewFragment("https://www.flipkart.com/search?q=creatine+monohydrate"));
        option3.setOnClickListener(v -> openWebViewFragment("https://www.flipkart.com/search?q=multivitamins"));
        option4.setOnClickListener(v -> openWebViewFragment("https://www.flipkart.com/search?q=fish+oil+capsules"));

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
