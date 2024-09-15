package com.example.fitnext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Integer> songList;
    private String[] songNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        listView = findViewById(R.id.listView);

        // Create an array list to store song resource IDs
        songList = new ArrayList<>();
        songList.add(R.raw.awake);
        songList.add(R.raw.acoustic_guitar);
        songList.add(R.raw.childdreams);
        songList.add(R.raw.fire);
        songList.add(R.raw.forestwalk);
        songList.add(R.raw.lullaby);
        songList.add(R.raw.melody);
        songList.add(R.raw.sakura);
        songList.add(R.raw.waterfall);
        songList.add(R.raw.whale);
        // Add more songs as needed

        // Define song names directly in the code
        songNames = new String[]{"Awake", "Acoustic Guitar","Child Dreams","Fire","ForestWalk","Lullaby","Melody","Sakura","Waterfall","Whale"}; // Add more song names as needed

        // Create an array adapter to display song names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songNames);
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start PlaySong activity with the selected song's position and the arrays of song IDs and names
                Intent intent = new Intent(SongActivity.this, PlaySong.class);
                intent.putExtra("position", position);
                intent.putExtra("songList", songList);
                intent.putExtra("songNames", songNames);
                startActivity(intent);
            }
        });
    }
}
