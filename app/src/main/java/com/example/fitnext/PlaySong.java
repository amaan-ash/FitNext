package com.example.fitnext;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {

    // Variables declaration
    private TextView textView;
    private ImageView play, previous, next;
    private MediaPlayer mediaPlayer;
    private String textContent;
    private int position;
    private SeekBar seekBar;
    private ArrayList<Integer> songList;
    private String[] songNames;
    private Thread updateSeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        // Initialize views
        textView = findViewById(R.id.textView);
        play = findViewById(R.id.play);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekBar);

        // Get data from intent
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        songNames = intent.getStringArrayExtra("songNames");
        songList = intent.getIntegerArrayListExtra("songList");

        // Set the song name on TextView
        textView.setText(songNames[position]); // Assuming position is the index of the current song
        textView.setSelected(true);

        // Initialize MediaPlayer with the selected song
        mediaPlayer = MediaPlayer.create(this, songList.get(position));
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());

        // Set up SeekBar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Update SeekBar position in a separate thread
        updateSeek = new Thread() {
            @Override
            public void run() {
                try {
                    while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        final int currentPosition = mediaPlayer.getCurrentPosition();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                seekBar.setProgress(currentPosition);
                            }
                        });
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();

        // Set up click listeners for play, previous, and next buttons
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.play);
                    // Stop the updateSeek thread
                    if (updateSeek != null && updateSeek.isAlive()) {
                        updateSeek.interrupt();
                    }
                } else {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                    // Start a new thread to update SeekBar position
                    updateSeek = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                    int currentPosition = mediaPlayer.getCurrentPosition();
                                    seekBar.setProgress(currentPosition);
                                    sleep(1000);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    updateSeek.start();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSong(-1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSong(1);
            }
        });
    }

    private void changeSong(int direction) {
        boolean isPaused = !mediaPlayer.isPlaying(); // Check if the MediaPlayer is paused before switching songs

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset(); // Reset the MediaPlayer
        }
        position = (position + direction + songNames.length) % songNames.length;
        String currentSongName = songNames[position];
        textContent = currentSongName;
        textView.setText(textContent);

        // Initialize MediaPlayer with the selected song
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + songList.get(position)));
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Reset SeekBar progress to 0
        seekBar.setProgress(0);

        // Stop the updateSeek thread if it's running
        if (updateSeek != null && updateSeek.isAlive()) {
            updateSeek.interrupt();
        }

        // Start a new thread to update SeekBar position
        updateSeek = new Thread() {
            @Override
            public void run() {
                try {
                    while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        int currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();

        // Update play/pause button state
        if (isPaused) {
            play.setImageResource(R.drawable.play);
        } else {
            play.setImageResource(R.drawable.pause);
        }

        // If the MediaPlayer was paused, automatically start playing the next song
        if (isPaused) {
            mediaPlayer.start();
            play.setImageResource(R.drawable.pause);
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        updateSeek.interrupt();
    }
}
