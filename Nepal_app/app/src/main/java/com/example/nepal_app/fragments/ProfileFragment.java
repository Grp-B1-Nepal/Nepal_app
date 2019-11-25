package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FrameLayout child_frame_1;
    private FrameLayout child_frame_2;
    private View rod;

    public ProfileFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        rod = inflater.inflate(R.layout.fragment_profile, container, false);

        child_frame_1 = rod.findViewById(R.id.child_topbar1);
        child_frame_2 = rod.findViewById(R.id.child_topbar2);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        ChildbarFragment Childbarfragment = new ChildbarFragment();
        ChildbarFragment Childbarfragment2 = new ChildbarFragment();
        fragmentTransaction.replace(R.id.child_topbar1, Childbarfragment);
        fragmentTransaction.replace(R.id.child_topbar2, Childbarfragment2);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return rod;
    }
}
