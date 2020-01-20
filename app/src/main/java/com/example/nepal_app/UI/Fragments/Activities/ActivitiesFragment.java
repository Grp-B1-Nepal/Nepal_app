package com.example.nepal_app.UI.Fragments.Activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.Logic.Objects.ChildObj;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * This fragment is shown when the user navigates to the activities. It can be achieved from the front page or the navigation bar.
 *
 */
public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView Singing, Talking, Tummy_Time, Cuddling_Time, Play_Time, Reading, SoundSinging, SoundTalking, SoundTummy_Time, SoundCuddling_Time, SoundPlay_Time, SoundReading;
    private View rod;
    private Button months04, months58, months912;
    private int agerange = 1;
    private int imagenum = 0;
    private ChildInfo childInfo;
    private ArrayList<ChildObj> childList = new ArrayList<>();


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
        if (rod == null ) {
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

            topbuttoncheck();

        }
            return rod;
    }

    public void topbuttoncheck () {

        childInfo = ChildInfo.getInstance();
        childList = childInfo.getChildArr(getContext());
        //If the childlist is 0 we just want this one shown
        if (childList.size() == 0 ) {
            //months04.getBackground().setColorFilter(getResources().getColor(R.color.buttongrey), PorterDuff.Mode.MULTIPLY);
            months04.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));

            //Agerange will only be 0 the first time you enter, therefore we need it to select based upon the age of the current child.
        } else if (agerange == 0){
            childInfo.getMonthProgress();
            switch (childInfo.getMonthProgress()) {
                case 1:
                case 2:
                case 3:
                case 4:
                    agerange = 1;
                    months04.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    agerange = 2;
                    months58.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
                    break;

                default:
                    agerange = 3;
                    months912.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
                    break;
            }
            //theese 3 if statements serve their purpose upon back pressed. It remembers the it when another fragment is inflated to remember the users selection.
        } else if (agerange == 1) {
            months04.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
        } else if (agerange == 2) {
            months58.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
        } else if (agerange == 3) {
            months58.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
        }
    }

    //TODO do something about the sound list, find out what the good way to go about it is. Can i save multiple raw files in an array?
    @Override
    public void onClick(View v) {

        if (v == Singing || v == Talking || v == Tummy_Time || v == Cuddling_Time || v == Play_Time || v == Reading) {
            //There are two different things supposed to be done on click and both the different types are image views, therefore this huge if statement.
            //For now the soundlist is just a sample. This has to be different based on agerange and the button clicked, when sound files are generated.
            Fragment information_fragment = new fragment_activity_information(onclickactivity(v));
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, information_fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (v == SoundCuddling_Time || v == SoundPlay_Time || v == SoundReading || v == SoundSinging || v == SoundTalking || v == SoundTummy_Time) {
//TODO add some sound files
        } else if (v == months04 || v == months58 || v == months912) {
            //Resets the color of all the buttons before it paints the background on one of them.
            months04.setBackground(getResources().getDrawable(R.drawable.buttonshape));
            months58.setBackground(getResources().getDrawable(R.drawable.buttonshape));
            months912.setBackground(getResources().getDrawable(R.drawable.buttonshape));


            //Paints the background and changes the informationnum range number. The number is passed on to the next fragment such that it knows what information it should get.
            if (v == months04) {
                agerange = 1;
                v.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));

            } else if (v == months58) {
                agerange = 2;
                v.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
            } else if (v == months912) {
                agerange = 3;
                v.setBackground(getResources().getDrawable(R.drawable.buttonshape_activities_gray));
            }
        }
    }

    /**
     * In the future this should also return the list of sounds.
     * Huge on click method
     * @param v
     * @return
     */
    public ActivityPOJO onclickactivity(View v) {

        ArrayList<Integer> arraylist = new ArrayList<>();

        ActivityPOJO activityPOJO = new ActivityPOJO(" ", 0 ,arraylist, imagenum);

        ArrayList<Integer> soundlist = new ArrayList<>();
        soundlist.add(R.raw.activitiestts);
        soundlist.add(R.raw.activitiestts);
        soundlist.add(R.raw.activitiestts);
        soundlist.add(R.raw.activitiestts);

        //TODO find sound clips.
        if (v == Singing) {
            imagenum = R.drawable.activity_singing;
            activityPOJO.setHeadlinetext("Singing for your child and it's benefits");

            if (agerange == 1) {
                soundlist.clear();
                soundlist.add(R.raw.singing11);
                soundlist.add(R.raw.singing12);
                soundlist.add(R.raw.singing13);
                soundlist.add(R.raw.singing14);

                activityPOJO.setInformationnum(R.array.activities_information_Singing_category1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_Singing_category2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_Singing_category3);
                activityPOJO.setSoundnumberlist(soundlist);
            }


        } else if (v == Talking) {
            imagenum = R.drawable.activity_talking;
            activityPOJO.setHeadlinetext("Talking to your child and it's benefits");

            if (agerange == 1) {
                activityPOJO.setInformationnum(R.array.activities_information_Talking_category1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_Talking_category2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_Talking_category3);
                activityPOJO.setSoundnumberlist(soundlist);
            }


        } else if (v == Tummy_Time) {
            imagenum = R.drawable.activity_tummytime;
            activityPOJO.setHeadlinetext("How a little tummy time develops your childs abilities.");

            if (agerange == 1) {
                activityPOJO.setInformationnum(R.array.activities_information_TummyTime_category1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_TummyTime_category2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_TummyTime_category3);
                activityPOJO.setSoundnumberlist(soundlist);
            }


        } else if (v == Cuddling_Time) {
            imagenum = R.drawable.activity_cuddling;
            activityPOJO.setHeadlinetext("Cuddling increases the bond between the mother and the baby.");

            if (agerange == 1) {
                activityPOJO.setInformationnum(R.array.activities_information_CuddlingTime_category1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_CuddlingTime_category2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_CuddlingTime_category3);
                activityPOJO.setSoundnumberlist(soundlist);
            }


        } else if (v == Play_Time) {
            imagenum = R.drawable.activity_playtime;
            activityPOJO.setHeadlinetext("Play time to increase your childs awareness");

            if (agerange == 1) {
                activityPOJO.setInformationnum(R.array.activities_information_PlayTimecategory1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_PlayTimecategory2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_PlayTimecategory3);
                activityPOJO.setSoundnumberlist(soundlist);
            }


        } else if (v == Reading) {
            imagenum = R.drawable.activity_reading;
            activityPOJO.setHeadlinetext("Reading for your child to improve it's reading abilities");

            if (agerange == 1) {
                activityPOJO.setInformationnum(R.array.activities_information_Readingcategory1);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 2) {
                activityPOJO.setInformationnum(R.array.activities_information_Readingcategory2);
                activityPOJO.setSoundnumberlist(soundlist);
            } else if (agerange == 3) {
                activityPOJO.setInformationnum(R.array.activities_information_Readingcategory3);
                activityPOJO.setSoundnumberlist(soundlist);
            }
        }
        activityPOJO.setImagenum(imagenum);
        return activityPOJO;
    }

    // POGO for grouping multiple fields
    final class ActivityPOJO {
        public String headlinetext;
        public int informationnum;
        public ArrayList<Integer> soundnumberlist;
        public int imagenum;

        public ActivityPOJO(String headlinetext, int informationnum, ArrayList<Integer> soundnumberlist, int imagenum) {
            this.headlinetext = headlinetext;
            this.informationnum = informationnum;
            this.soundnumberlist = soundnumberlist;
            this.imagenum = imagenum;
        }

        public ArrayList<Integer> getSoundnumberlist() { return soundnumberlist; }

        public int getImagenum() { return imagenum; }

        public int getInformationnum() { return informationnum; }

        public String getHeadlinetext() { return headlinetext; }

        public void setHeadlinetext(String headlinetext) { this.headlinetext = headlinetext; }

        public void setInformationnum(int informationnum) { this.informationnum = informationnum; }

        public void setSoundnumberlist(ArrayList<Integer> soundnumberlist) { this.soundnumberlist = soundnumberlist; }

        public void setImagenum(int imagenum) { this.imagenum = imagenum; }
    }
}
