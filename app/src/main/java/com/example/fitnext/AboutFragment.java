package com.example.fitnext;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_about, container, false);
        TextView gitHubLink =view.findViewById(R.id.githublink);
        gitHubLink.setPaintFlags(gitHubLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        gitHubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String githubUrl = "https://github.com/amaan0707";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                startActivity(intent);
            }
        });
        return view;
    }
}