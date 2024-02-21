package com.example.fitnext;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MeditateSongFragment extends Fragment {
    MediaPlayer music1=null,music2=null,music3=null,music4=null,music5=null,music6=null,music7=null,music8=null,music9=null,music10=null;
    Button b1,p1,s1,b2,p2,s2,b3,p3,s3,b4,p4,s4,b5,p5,s5,b6,p6,s6,b7,p7,s7,b8,p8,s8,b9,p9,s9,b10,p10,s10;

    public MeditateSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meditate_song, container, false);


        music1=MediaPlayer.create(getContext(),R.raw.acoustic_guitar);
        music2= MediaPlayer.create(getContext(),R.raw.fire);
        music3=MediaPlayer.create(getContext(),R.raw.waterfall);
        music4= MediaPlayer.create(getContext(),R.raw.whale);
        music5=MediaPlayer.create(getContext(),R.raw.childdreams);
        music6=MediaPlayer.create(getContext(),R.raw.awake);
        music7=MediaPlayer.create(getContext(),R.raw.forestwalk);
        music8=MediaPlayer.create(getContext(),R.raw.lullaby);
        music9=MediaPlayer.create(getContext(),R.raw.melody);
        music10= MediaPlayer.create(getContext(),R.raw.sakura);

        b1 = view.findViewById(R.id.play1);
        p1 = view.findViewById(R.id.pause1);
        s1 = view.findViewById(R.id.stop1);

        b2 = view.findViewById(R.id.play2);
        p2 = view.findViewById(R.id.pause2);
        s2 = view.findViewById(R.id.stop2);

        b3 = view.findViewById(R.id.play3);
        p3 = view.findViewById(R.id.pause3);
        s3 = view.findViewById(R.id.stop3);

        b4 = view.findViewById(R.id.play4);
        p4 = view.findViewById(R.id.pause4);
        s4 = view.findViewById(R.id.stop4);

        b5 = view.findViewById(R.id.play5);
        p5 = view.findViewById(R.id.pause5);
        s5 = view.findViewById(R.id.stop5);

        b6 = view.findViewById(R.id.play6);
        p6 = view.findViewById(R.id.pause6);
        s6 = view.findViewById(R.id.stop6);

        b7 = view.findViewById(R.id.play7);
        p7 = view.findViewById(R.id.pause7);
        s7 = view.findViewById(R.id.stop7);

        b8 = view.findViewById(R.id.play8);
        p8 = view.findViewById(R.id.pause8);
        s8 = view.findViewById(R.id.stop8);

        b9=view.findViewById(R.id.play9);
        p9=view.findViewById(R.id.pause9);
        s9=view.findViewById(R.id.stop9);

        b10=view.findViewById(R.id.play10);
        p10=view.findViewById(R.id.pause10);
        s10=view.findViewById(R.id.stop10);

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
                music1=MediaPlayer.create(getContext(), R.raw.acoustic_guitar);

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
                music2=MediaPlayer.create(getContext(), R.raw.fire);

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
                music3=MediaPlayer.create(getContext(),R.raw.waterfall);
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
                music4=MediaPlayer.create(getContext(),R.raw.whale);
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
                music5=MediaPlayer.create(getContext(),R.raw.awake);
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
                music6=MediaPlayer.create(getContext(),R.raw.childdreams);
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
                music7=MediaPlayer.create(getContext(),R.raw.forestwalk);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stopAllSongsExcept(music8);
                music8.start();
            }
        });

        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music8!=null)
                    music8.pause();

            }
        });

        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music8.stop();
                music8=MediaPlayer.create(getContext(),R.raw.lullaby);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stopAllSongsExcept(music9);
                music9.start();
            }
        });

        p9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music9!=null){
                    music9.pause();
                }
            }
        });

        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music9.stop();
                music9=MediaPlayer.create(getContext(),R.raw.melody);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllSongsExcept(music10);
                music10.start();
            }
        });

        p10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music10!=null){
                    music10.pause();
                }
            }
        });

        s10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music10.stop();
                music10=MediaPlayer.create(getContext(),R.raw.sakura);
            }
        });

        music1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music1.stop();
                // Prepare the next song
                music2.prepareAsync();
                // Start playing the next song
                music2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music2.stop();
                // Prepare the next song
                music3.prepareAsync();
                // Start playing the next song
                music3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music3.stop();
                // Prepare the next song
                music4.prepareAsync();
                // Start playing the next song
                music4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music5.stop();
                // Prepare the next song
                music6.prepareAsync();
                // Start playing the next song
                music6.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music6.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music6.stop();
                // Prepare the next song
                music7.prepareAsync();
                // Start playing the next song
                music7.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music7.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music7.stop();
                // Prepare the next song
                music8.prepareAsync();
                // Start playing the next song
                music8.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music8.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music8.stop();
                // Prepare the next song
                music9.prepareAsync();
                // Start playing the next song
                music9.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        music9.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the current song
                music9.stop();
                // Prepare the next song
                music10.prepareAsync();
                // Start playing the next song
                music10.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
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
        if (music8 != mediaPlayerToKeepPlaying && music8.isPlaying()) {
            music8.stop();
            music8.prepareAsync();
        }
        if (music9 != mediaPlayerToKeepPlaying && music9.isPlaying()) {
            music9.stop();
            music9.prepareAsync();
        }
        if (music10!=mediaPlayerToKeepPlaying && music10.isPlaying()){
            music10.stop();
            music10.prepareAsync();
        }


    }



}