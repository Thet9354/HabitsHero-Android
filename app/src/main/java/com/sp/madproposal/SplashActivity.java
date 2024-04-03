package com.sp.madproposal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton; // Import ImageButton

import com.sp.madproposal.Onboarding.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageButton btnToggleMusic; // Declare ImageButton
    private boolean isMusicOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.s1_splash);

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.splash);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.start();

        // Initialize toggle button
        btnToggleMusic = findViewById(R.id.soundbutton); // Replace with your actual button ID
        btnToggleMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic();
            }
        });

        // Set a timer to navigate to the main activity after a certain duration
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000); // Adjust the duration as needed (in milliseconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop(); // Stop the music when navigating to the next activity
                    }
                    Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }

    private void toggleMusic() {
        if (isMusicOn) {
            mediaPlayer.pause();
            btnToggleMusic.setImageResource(R.drawable.soundoff); // Change to your 'music off' icon
            isMusicOn = false;
        } else {
            mediaPlayer.start();
            btnToggleMusic.setImageResource(R.drawable.sound); // Change to your 'music on' icon
            isMusicOn = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            finish();
        }
    }
}
