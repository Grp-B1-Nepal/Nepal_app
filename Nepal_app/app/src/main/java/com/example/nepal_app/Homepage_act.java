package com.example.nepal_app;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage_act extends AppCompatActivity implements View.OnClickListener {

    private ImageView recipesI, developI, activitiesI, recipSound, develoSound, activitySound;
    private Button recipeB, developB, activitesB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);

        //settin up images
        recipesI = findViewById(R.id.imageRecipe);
        developI = findViewById(R.id.imageDevelopment);
        activitiesI = findViewById(R.id.imageActivities);
        recipSound = findViewById(R.id.speakerRecipe);
        develoSound = findViewById(R.id.speakerDevelop);
        activitySound = findViewById(R.id.speakerActivity);

        //setting up buttons
        recipeB = findViewById(R.id.recipesButton);
        developB = findViewById(R.id.developButton);
        activitesB = findViewById(R.id.activitiesButton);

        //setting text on buttons
        recipeB.setText("Go to recipes");
        developB.setText("Go to development");
        activitesB.setText("Go to activities");

        //setting up listeners
        recipeB.setOnClickListener(this);
        developB.setOnClickListener(this);
        activitesB.setOnClickListener(this);
        recipSound.setOnClickListener(this);
        develoSound.setOnClickListener(this);
        activitySound.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == recipeB) {
            //Intent i = new Intent(this, reipes_akt.class);
            //startActivity(i);
        }
        else if(v == developB) {
            //Intent i = new Intent(this, development_akt.class);
            //startActivity(i);
        }
        else if(v == activitesB){
            //Intent i = new Intent(this, activities_akt.class);
            //startActivity(i);
        }
        else if(v == recipSound){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.meow);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
        else if(v == develoSound){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.meow);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
        else if(v == activitySound){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.meow);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }

    }
}
