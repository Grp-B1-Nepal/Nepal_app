package com.example.nepal_app;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
=======
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.Fragments.ActivitiesFragment;
import com.example.nepal_app.Fragments.HomeFragment;
import com.example.nepal_app.Fragments.ProfileFragment;
import com.example.nepal_app.Fragments.ProgressFragment;
import com.example.nepal_app.Fragments.RecipesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
>>>>>>> b946162a32a588aa2aaa9d45411df382b2435020

public class recipe_home extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    //TODO remove search bar from recipemain and into activity_main
    //TODO searchbar functionality
    //TODO Top buttons (All, favorite, Today) functionality

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;

    private ArrayList<Integer> imageViews = new ArrayList<>();
    private ArrayList<String> recipeNames = new ArrayList<>();

    private OnNoteListener onNoteListener;

    public void fillLists() {
        imageViews.add(R.drawable.dal);
        imageViews.add(R.drawable.cake);
        imageViews.add(R.drawable.banana);
        imageViews.add(R.drawable.mos);

        recipeNames.add("Dal");
        recipeNames.add("Cake");
        recipeNames.add("Banana");
        recipeNames.add("Mash");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        fillLists();

        recyclerView = findViewById(R.id.rcvrecipes);

        recyclerView.setHasFixedSize(true);

        rcAdapter = new RecipeAdapter(imageViews, recipeNames, onNoteListener);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);


    }

    public interface OnNoteListener extends RecipeAdapter.OnNoteListener {
        void onNoteClick(int position);
    }
}
=======
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new HomeFragment());
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.navigation_activities:
                            openFragment(new ActivitiesFragment());
                            return true;
                        case R.id.navigation_profile:
                            openFragment(new ProfileFragment());
                            return true;
                        case R.id.navigation_progress:
                            openFragment(new ProgressFragment());
                            return true;
                        case R.id.navigation_recipe:
                            openFragment(new recipe());
                            return true;
                    }
                    return false;
                }
            };
    } //ellers lav aktiviterer mellem de andres? ved godt vi ikke har aftalt hvordan vi gør det
>>>>>>> b946162a32a588aa2aaa9d45411df382b2435020
