package com.example.mindwind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mindwind.Common.LoginSignup.UserStartUpScreen;
import com.example.mindwind.Common.Startpage;
import com.example.mindwind.User.AnxietyPage;
import com.example.mindwind.User.BookRecommendation;
import com.example.mindwind.User.Bookpage;
import com.example.mindwind.User.ImageSlider;
import com.example.mindwind.User.JournalPage;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callBookPage(View view){
        startActivity(new Intent(Dashboard.this, Bookpage.class));

    }

    public void callImageViewer(View view){
        startActivity(new Intent(Dashboard.this, ImageSlider.class));

    }

    public void callAnxietyPage(View view){
        startActivity(new Intent(Dashboard.this, AnxietyPage.class));

    }
    public void callBookRecommendation(View view){
        startActivity(new Intent(Dashboard.this, BookRecommendation.class));

    }

    public void callJournalPage(View view){
        startActivity(new Intent(Dashboard.this, JournalPage.class));
    }

}