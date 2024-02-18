package com.example.fitnext;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitnext.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link meditation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeditationExercisesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button b1,b2,b3,b4,b41,b51,b61, p1,p2,p3,p4,p41,p51,p61, s1,s2,s3,s4,s41,s51,s61;
    MediaPlayer music1 = null,music2 = null,music3 = null,music4 = null,music41 = null,music51 = null,music61 = null;
    public MeditationExercisesFragment() {
        // Required empty public constructor
    }



    public static MeditationExercisesFragment newInstance(String param1, String param2) {
        MeditationExercisesFragment fragment = new MeditationExercisesFragment();
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
        View view=inflater.inflate(R.layout.fragment_meditation_exercises, container, false);

        music1=MediaPlayer.create(getActivity(), R.raw.one_min);
        music2=MediaPlayer.create(getActivity(), R.raw.four_min);
        music3=MediaPlayer.create(getActivity(), R.raw.ten_min);
        music4=MediaPlayer.create(getActivity(), R.raw.fifteen_min);
        music41=MediaPlayer.create(getActivity(), R.raw.body_scan);
        music51=MediaPlayer.create(getActivity(), R.raw.fifteen_min_rebounce);
        music61=MediaPlayer.create(getActivity(), R.raw.mindful_eating);

        b1=view.findViewById(R.id.play1);
        p1=view.findViewById(R.id.pause1);
        s1=view.findViewById(R.id.stop1);

        b2=view.findViewById(R.id.play2);
        p2=view.findViewById(R.id.pause2);
        s2=view.findViewById(R.id.stop2);

        b3=view.findViewById(R.id.play3);
        p3=view.findViewById(R.id.pause3);
        s3=view.findViewById(R.id.stop3);

        b4=view.findViewById(R.id.play4);
        p4=view.findViewById(R.id.pause4);
        s4=view.findViewById(R.id.stop4);

        b41=view.findViewById(R.id.play41);
        p41=view.findViewById(R.id.pause41);
        s41=view.findViewById(R.id.stop41);

        b51=view.findViewById(R.id.play51);
        p51=view.findViewById(R.id.pause51);
        s51=view.findViewById(R.id.stop51);

        b61=view.findViewById(R.id.play61);
        p61=view.findViewById(R.id.pause61);
        s61=view.findViewById(R.id.stop61);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music1.start();

            }
        });


        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music1!=null)
                    music1.pause();

            }
        });


        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music1.stop();
                music1=MediaPlayer.create(getActivity(), R.raw.one_min);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music2.start();

            }
        });


        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music2!=null)
                    music2.pause();

            }
        });


        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music2.stop();
                music2=MediaPlayer.create(getActivity(), R.raw.four_min);


            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music3.start();

            }
        });



        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music3!=null)
                    music3.pause();

            }
        });


        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music3.stop();
                music3=MediaPlayer.create(getActivity(), R.raw.ten_min);


            }
        });



        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music4.start();

            }
        });



        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music4!=null)
                    music4.pause();

            }
        });


        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music4.stop();
                music4=MediaPlayer.create(getActivity(), R.raw.fifteen_min);

            }
        });



        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music41.start();


            }
        });


        p41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music41!=null)
                    music41.pause();

            }
        });


        s41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music41.stop();
                music41=MediaPlayer.create(getActivity(), R.raw.body_scan);

            }
        });


        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music51.start();


            }
        });


        p51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music51!=null)
                    music51.pause();

            }
        });


        s51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music51.stop();
                music51=MediaPlayer.create(getActivity(), R.raw.fifteen_min_rebounce);


            }
        });


        b61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music61.start();


            }
        });


        p61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music61!=null)
                    music61.pause();

            }
        });


        s61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                music61.stop();
                music61=MediaPlayer.create(getActivity(), R.raw.mindful_eating);

            }
        });

        return view;
    }
}