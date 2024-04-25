package com.example.musics;

import static java.lang.System.*;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatButton btn1, btn2, btn3, btn4;
        btn1 = findViewById(R.id.pause);
        btn2 = findViewById(R.id.play);
        btn3 = findViewById(R.id.stop);
        btn4 = findViewById(R.id.skip);

        MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        String path = "android.resource://"+getPackageName()+"/raw/prakkathan";
        String online = "https://drive.google.com/file/d/1y_spcVIb8QIObEpydXak6ur7s9lnwrT8/view?usp=sharing.mp3";

        Uri audioURI = Uri.parse(path);
       Uri onlineUri = Uri.parse(online);

        try {
            mp.setDataSource(this, audioURI);
            mp.prepare();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                mp.seekTo(0);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mp.getCurrentPosition();
                int newPosition = currentPosition + 10000;
                mp.seekTo(newPosition);
            }
        });
    }
}