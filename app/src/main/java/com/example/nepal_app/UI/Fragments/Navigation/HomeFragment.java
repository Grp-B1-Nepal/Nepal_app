package com.example.nepal_app.UI.Fragments.Navigation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.UI.Fragments.Activities.ActivitiesFragment;
import com.example.nepal_app.UI.Fragments.Profile.ProfileFragment;
import com.example.nepal_app.UI.Fragments.Progress.ProgressFragment;
import com.example.nepal_app.UI.Fragments.Recipes.RecipeHome;
import com.example.nepal_app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private ImageButton activitiesB, activitesSound, profileB, profileSound,
            progressB, progressSound, recipesB, recipesSound, quizB, quizSound, comicB, comicSound;
    private View rod;
    private Boolean isSoundPlaying = false;

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

            if(rod == null) {
            rod = inflater.inflate(R.layout.fragment_homepage, container, false);

            //setting up buttons
            activitiesB = rod.findViewById(R.id.activitesButton);
            activitesSound = rod.findViewById(R.id.activitesSpeaker);
            profileB = rod.findViewById(R.id.profileButton);
            profileSound = rod.findViewById(R.id.profileSpeaker);
            progressB = rod.findViewById(R.id.progressButton);
            progressSound = rod.findViewById(R.id.progressSpeaker);
            recipesB = rod.findViewById(R.id.recipesButton);
            recipesSound = rod.findViewById(R.id.recipesSpeaker);
            quizB = rod.findViewById(R.id.quizButton);
            quizSound = rod.findViewById(R.id.quizSpeaker);
            comicB = rod.findViewById(R.id.comicButton);
            comicSound = rod.findViewById(R.id.comicSpeaker);

            //setting up onclicklisteners
            activitiesB.setOnClickListener(this);
            activitesSound.setOnClickListener(this);
            profileB.setOnClickListener(this);
            profileSound.setOnClickListener(this);
            progressB.setOnClickListener(this);
            progressSound.setOnClickListener(this);
            recipesB.setOnClickListener(this);
            recipesSound.setOnClickListener(this);
            quizB.setOnClickListener(this);
            quizSound.setOnClickListener(this);
            comicB.setOnClickListener(this);
            comicSound.setOnClickListener(this);

            }
        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == recipesB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new RecipeHome());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == progressB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new ProgressFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == activitiesB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new ActivitiesFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == recipesSound) {
            if (!isSoundPlaying) {
                isSoundPlaying = true;
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.homepage_recipe);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        isSoundPlaying = false;
                    }
                });
            }
        } else if (v == progressSound) {
            if (!isSoundPlaying) {
                isSoundPlaying = true;
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.homepage_progress);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        isSoundPlaying = false;
                    }
                });
            }
        } else if (v == activitesSound) {
            if(!isSoundPlaying) {
                isSoundPlaying = true;
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.homepage_activity);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        isSoundPlaying = false;
                    }
                });
            }
        } else if (v == profileB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new ProfileFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == profileSound) {
            if(!isSoundPlaying) {
                isSoundPlaying = true;
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.homepage_profile);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        isSoundPlaying = false;
                    }
                });
            }
        } else if (v == comicB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new UDFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (v == quizB) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new UDFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
