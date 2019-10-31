package com.example.nepal_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homepage_act extends AppCompatActivity implements View.OnClickListener {

    private ImageView recipesI, developI, activitiesI;
    private Button recipeB, developB, activitesB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);

        //settin up images
        recipesI = findViewById(R.id.imageRecipe);
        developI = findViewById(R.id.imageDevelopment);
        activitiesI = findViewById(R.id.imageActivities);

        //setting up buttons
        recipeB = findViewById(R.id.recipesButton);
        developB = findViewById(R.id.developButton);
        activitesB = findViewById(R.id.activitiesButton);

        //setting up listeners
        recipeB.setOnClickListener(this);
        developB.setOnClickListener(this);
        activitesB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == recipeB) {
            System.out.println("do nothing");
        }
        else if(v == developB) {
            System.out.println("do nothing");
        }
        else if(v == activitesB){
            System.out.println("do nothing");
        }

    }
}
