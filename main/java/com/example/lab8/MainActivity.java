package com.example.lab8;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnNext;
    private int currentImageIndex = 0;
    private int[] imageIds;
    private MediaPlayer mediaPlayer;
    private VideoView videoView; // Для связи с видео, если нужно

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация массива ID изображений
        imageIds = new int[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3
        };

        imageView = findViewById(R.id.imageView);
        btnNext = findViewById(R.id.btnNext);

        // Настройка фонового аудио
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageIds.length;
                imageView.setImageResource(imageIds[currentImageIndex]);
            }
        });
    }

    // Для управления аудио при взаимодействии с видео
    public void pauseBackgroundAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumeBackgroundAudio() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.start();
                }
            }, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
