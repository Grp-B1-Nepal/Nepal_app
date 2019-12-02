package com.example.nepal_app.Fragments.Navigation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.Fragments.Activities.ActivitiesFragment;
import com.example.nepal_app.Fragments.Progress.ProgressFragment;
import com.example.nepal_app.Fragments.Recipes.RecipeHome;
import com.example.nepal_app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private ImageView recipesI, developI, activitiesI, recipSound, develoSound, activitySound;
    private Button recipeB, developB, activitesB;
    private View rod;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment com.example.navigationbarapp.NavigationBarFragments.HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            rod = inflater.inflate(R.layout.homepage_layout, container, false);

            //settin up images
            recipesI = rod.findViewById(R.id.imageRecipe);
            developI = rod.findViewById(R.id.imageDevelopment);
            activitiesI = rod.findViewById(R.id.imageActivities);
            recipSound = rod.findViewById(R.id.speakerRecipe);
            develoSound = rod.findViewById(R.id.speakerDevelop);
            activitySound = rod.findViewById(R.id.speakerActivity);

            //setting up buttons
            recipeB = rod.findViewById(R.id.recipesButton);
            developB = rod.findViewById(R.id.developButton);
            activitesB = rod.findViewById(R.id.activitiesButton);

            //setting text on buttons
            recipeB.setText("Go to recipes");
            developB.setText("Go to progress");
            activitesB.setText("Go to activities");

            //setting up listeners
            recipeB.setOnClickListener(this);
            developB.setOnClickListener(this);
            activitesB.setOnClickListener(this);
            recipSound.setOnClickListener(this);
            develoSound.setOnClickListener(this);
            activitySound.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == recipeB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new RecipeHome());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == developB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new ProgressFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == activitesB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new ActivitiesFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == recipSound) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.recipestts);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } else if (v == develoSound) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.progresstts);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } else if (v == activitySound) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.activitiestts);
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
