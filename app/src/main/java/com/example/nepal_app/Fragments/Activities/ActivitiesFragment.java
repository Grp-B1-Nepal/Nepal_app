package com.example.nepal_app.Fragments.Activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtSinging, txtTalking, txtTummy_Time, txtCuddling_Time, txtPlay_Time, txtReading;
    private ImageView Singing, Talking, Tummy_Time, Cuddling_Time, Play_Time, Reading, SoundSinging, SoundTalking, SoundTummy_Time, SoundCuddling_Time, SoundPlay_Time, SoundReading;
    private View rod;
    private Button months04, months58, months912;
    private int agerange = 1;




    public ActivitiesFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rod = inflater.inflate(R.layout.fragment_activity, container, false);

        Singing = rod.findViewById(R.id.activities_imageViewSinging);
        Talking = rod.findViewById(R.id.activities_imageViewTalking);
        Tummy_Time = rod.findViewById(R.id.activities_imageViewTummyTime);
        Cuddling_Time = rod.findViewById(R.id.activities_imageViewCuddlingTime);
        Play_Time = rod.findViewById(R.id.activities_imageViewPlayTime);
        Reading = rod.findViewById(R.id.activities_imageViewReading);

        SoundSinging = rod.findViewById(R.id.activities_soundSinging);
        SoundTalking = rod.findViewById(R.id.activities_soundTalking);
        SoundTummy_Time = rod.findViewById(R.id.activities_soundTummyTime);
        SoundCuddling_Time = rod.findViewById(R.id.activities_soundCuddlingTime);
        SoundPlay_Time = rod.findViewById(R.id.activities_soundPlayTime);
        SoundReading = rod.findViewById(R.id.activities_soundReading);

        months04 = rod.findViewById(R.id.activities_button04months);
        months58 = rod.findViewById(R.id.activities_button58months);
        months912 = rod.findViewById(R.id.activities_button912months);

        Singing.setOnClickListener(this);
        Talking.setOnClickListener(this);
        Tummy_Time.setOnClickListener(this);
        Cuddling_Time.setOnClickListener(this);
        Play_Time.setOnClickListener(this);
        Reading.setOnClickListener(this);

        SoundSinging.setOnClickListener(this);
        SoundTalking.setOnClickListener(this);
        SoundTummy_Time.setOnClickListener(this);
        SoundCuddling_Time.setOnClickListener(this);
        SoundPlay_Time.setOnClickListener(this);
        SoundReading.setOnClickListener(this);

        months04.setOnClickListener(this);
        months58.setOnClickListener(this);
        months912.setOnClickListener(this);

        //Months04 is chosen to be the default selection.
        //here i could probably do something else that is much more suitable for the task by using saved instace state or something like that.
        //This remembers what button was pressed if a user goes back to this view.
        if (agerange == 1) {
            months04.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        } else if (agerange == 2) {
            months58.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        } else if (agerange == 3) {
            months912.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        }
        return rod;
    }
//TODO do something about the sound list, find out what the good way to go about it is. Can i save multiple raw files in an array?
    @Override
    public void onClick(View v) {

        if (v == Singing || v == Talking || v == Tummy_Time || v == Cuddling_Time || v == Play_Time || v == Reading) {
            //There are two different things supposed to be done on click and both the different types are image views, therefore this huge if statement.
            int listnum = 0;
            int imagenum = R.drawable.mother_reading;
            ArrayList<Integer> soundlist = new ArrayList<>();
            if (v == Singing) {
                //imagenum = R.drawable.mother_Singing; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_Singing_category1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_Singing_category2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_Singing_category3;
                }


            } else if (v == Talking) {
                //imagenum = R.drawable.mother_Talking; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_Talking_category1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_Talking_category2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_Talking_category3;
                }


            } else if (v == Tummy_Time) {
//imagenum = R.drawable.mother_Talking; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_TummyTime_category1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_TummyTime_category2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_TummyTime_category3;
                }


            } else if (v == Cuddling_Time) {
//imagenum = R.drawable.mother_Talking; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_CuddlingTime_category1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_CuddlingTime_category2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_CuddlingTime_category3;
                }


            } else if (v == Play_Time) {
//imagenum = R.drawable.mother_Talking; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_PlayTimecategory1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_PlayTimecategory2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_PlayTimecategory3;
                }


            } else if (v == Reading) {
                //imagenum = R.drawable.mother_Talking; here there is supposed to be added a picture, but the picture isnt added yet.
                if (agerange == 1) {
                    listnum = R.array.activities_information_Readingcategory1;
                } else if (agerange == 2) {
                    listnum = R.array.activities_information_Readingcategory2;
                } else if (agerange == 3) {
                    listnum = R.array.activities_information_Readingcategory3;
                }
            }

            soundlist.add(R.raw.activitiestts);
            soundlist.add(R.raw.activitiestts);
            soundlist.add(R.raw.activitiestts);
            soundlist.add(R.raw.activitiestts);

            Fragment information_fragment = new fragment_activity_information(listnum, soundlist, imagenum);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, information_fragment);
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (v == SoundCuddling_Time || v == SoundPlay_Time || v == SoundReading || v == SoundSinging || v == SoundTalking || v == SoundTummy_Time) {

        } else if (v == months04 || v == months58 || v == months912) {

            months04.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
            months58.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                months912.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

          if (v == months04) {
            agerange = 1;
            v.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        } else if (v == months58) {
            agerange = 2;
            v.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        } else if (v == months912) {
            agerange = 3;
            v.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
        }
        }
        }
        }
