package com.example.nepal_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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

        //setting text on buttons
        recipeB.setText("Go to recipes");
        developB.setText("Go to development");
        activitesB.setText("Go to activities");

        //setting up listeners
        recipeB.setOnClickListener(this);
        developB.setOnClickListener(this);
        activitesB.setOnClickListener(this);
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

    }
}
