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


public class MeditationExercisesFragment extends Fragment {


    Button b1,b2,b3,b4,b5,b6,b7, p1,p2,p3,p4,p5,p6,p7, s1,s2,s3,s4,s5,s6,s7;
    MediaPlayer music1 = null,music2 = null,music3 = null,music4 = null,music5 = null,music6 = null,music7 = null;
    public MeditationExercisesFragment() {
        // Required empty public constructor
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
        music5=MediaPlayer.create(getActivity(), R.raw.body_scan);
        music6=MediaPlayer.create(getActivity(), R.raw.fifteen_min_rebounce);
        music7=MediaPlayer.create(getActivity(), R.raw.mindful_eating);

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

        b5=view.findViewById(R.id.play5);
        p5=view.findViewById(R.id.pause5);
        s5=view.findViewById(R.id.stop5);

        b6=view.findViewById(R.id.play6);
        p6=view.findViewById(R.id.pause6);
        s6=view.findViewById(R.id.stop6);

        b7=view.findViewById(R.id.play7);
        p7=view.findViewById(R.id.pause7);
        s7=view.findViewById(R.id.stop7);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllSongsExcept(music1);
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
                stopAllSongsExcept(music2);
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
                stopAllSongsExcept(music3);
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
                stopAllSongsExcept(music4);
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



        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllSongsExcept(music5);
                music5.start();


            }
        });


        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music5!=null)
                    music5.pause();

            }
        });


        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music5.stop();
                music5=MediaPlayer.create(getActivity(), R.raw.body_scan);

            }
        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllSongsExcept(music6);
                music6.start();


            }
        });


        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music6!=null)
                    music6.pause();

            }
        });


        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music6.stop();
                music6=MediaPlayer.create(getActivity(), R.raw.fifteen_min_rebounce);


            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllSongsExcept(music7);
                music7.start();


            }
        });


        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music7!=null)
                    music7.pause();

            }
        });


        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                music7.stop();
                music7=MediaPlayer.create(getActivity(), R.raw.mindful_eating);

            }
        });

        return view;
    }
    private void stopAllSongsExcept(MediaPlayer mediaPlayerToKeepPlaying) {
        if (music1 != mediaPlayerToKeepPlaying && music1.isPlaying()) {
            music1.stop();
            music1.prepareAsync(); // Resets the MediaPlayer to its uninitialized state
        }
        if (music2 != mediaPlayerToKeepPlaying && music2.isPlaying()) {
            music2.stop();
            music2.prepareAsync();
        }
        if (music3 != mediaPlayerToKeepPlaying && music3.isPlaying()) {
            music3.stop();
            music3.prepareAsync();
        }
        if (music4 != mediaPlayerToKeepPlaying && music4.isPlaying()) {
            music4.stop();
            music4.prepareAsync();
        }
        if (music5 != mediaPlayerToKeepPlaying && music5.isPlaying()) {
            music5.stop();
            music5.prepareAsync();
        }
        if (music6 != mediaPlayerToKeepPlaying && music6.isPlaying()) {
            music6.stop();
            music6.prepareAsync();
        }
        if (music7 != mediaPlayerToKeepPlaying && music7.isPlaying()) {
            music7.stop();
            music7.prepareAsync();
        }



    }

}