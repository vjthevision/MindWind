package com.example.mindwind.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.mindwind.Common.LoginSignup.SignUp;
import com.example.mindwind.Common.LoginSignup.UserStartUpScreen;
import com.example.mindwind.Dashboard;
import com.example.mindwind.R;

public class Startpage extends AppCompatActivity {

    VideoView videoView;
    SharedPreferences onBoadringScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
//        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.videoview);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.opening);

        videoView.setVideoURI(video);
        onBoadringScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        boolean isFirstTime = onBoadringScreen.getBoolean("firstTime", true);
        if (isFirstTime) {
            SharedPreferences.Editor editor = onBoadringScreen.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (isFinishing())
                        return;
                    startActivity(new Intent(Startpage.this, OnBoarding.class));
                    finish();
                }
            });

            videoView.start();
        }
        else{
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (isFinishing())
                        return;
                    startActivity(new Intent(Startpage.this, Dashboard.class));
                    finish();
                }
            });

            videoView.start();

        }
    }
}