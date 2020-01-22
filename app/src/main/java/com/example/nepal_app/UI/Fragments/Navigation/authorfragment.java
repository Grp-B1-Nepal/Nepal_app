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
public class authorfragment extends Fragment {

    private View rod;

    public authorfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment com.example.navigationbarapp.NavigationBarFragments.HomeFragment.
     */
    public static authorfragment newInstance() {
        authorfragment fragment = new authorfragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rod == null) {
            rod = inflater.inflate(R.layout.fragment_author, container, false);


        }
        return rod;
    }
}
