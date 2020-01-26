package com.example.nepal_app.UI.Fragments.Navigation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.UI.Fragments.Activities.ActivitiesFragment;
import com.example.nepal_app.UI.Fragments.Afspilning;
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


  private int[] knapperId = {
          R.id.activitesButton,
          R.id.profileButton,
          R.id.progressButton,
          R.id.recipesButton,
          R.id.quizButton,
          R.id.comicButton,
  };
  private int[] lydeKnapId = {
          R.id.activitesSpeaker,
          R.id.profileSpeaker,
          R.id.progressSpeaker,
          R.id.recipesSpeaker,
          R.id.quizSpeaker,
          R.id.comicSpeaker,
  };
  private int[] lydeId = {
          R.raw.homepage_activity,
          R.raw.homepage_profile,
          R.raw.homepage_progress,
          R.raw.homepage_recipe,
          0,
          0,
  };
    private View rod;
    private Button authorbutton;

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

            rod = inflater.inflate(R.layout.fragment_homepage, container, false);

            for (int rækkeNr = 0; rækkeNr< knapperId.length; rækkeNr++) {
              ImageButton aktivitetKnap = rod.findViewById(knapperId[rækkeNr]);
              aktivitetKnap.setOnClickListener(this);
              ImageButton lydKnap = rod.findViewById(lydeKnapId[rækkeNr]);
              if (lydeId[rækkeNr] != 0) {
                lydKnap.setOnClickListener(this);
              } else {
                lydKnap.setEnabled(false); // slå de knapper fra der ikke har lyd
              }
            }
            authorbutton = rod.findViewById(R.id.authorbutton);
            authorbutton.setOnClickListener(this);

      rod.findViewById(R.id.quizButton).setEnabled(false);
      rod.findViewById(R.id.comicButton).setEnabled(false);

        return rod;
    }

  @Override
  public void onPause() {
    Afspilning.stop();
    super.onPause();
  }

  @Override
    public void onClick(View v) {
      for (int rækkeNr = 0; rækkeNr< knapperId.length; rækkeNr++) {
        if (lydeKnapId[rækkeNr] == v.getId()) {
          Afspilning.start(MediaPlayer.create(getActivity(), lydeId[rækkeNr]), 700);
          return;
        }
      }

      Fragment fragment = null;
      if (v.getId()==R.id.activitesButton) fragment = new ActivitiesFragment();
      if (v.getId()==R.id.profileButton) fragment = new ProfileFragment();
      if (v.getId()==R.id.progressButton) fragment = new ProgressFragment();
      if (v.getId()==R.id.recipesButton) fragment = new RecipeHome();
      if (v.getId()==R.id.authorbutton) fragment = new authorfragment();

      if (fragment==null) {
        new IllegalStateException("Tryk på ukendt knap").printStackTrace();
        return;
      }
      FragmentTransaction transaction = getFragmentManager().beginTransaction();
      transaction.replace(R.id.container, fragment);
      transaction.addToBackStack(null);
      transaction.commit();
    }
}
