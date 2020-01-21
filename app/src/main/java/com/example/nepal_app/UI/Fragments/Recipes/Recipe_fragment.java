package com.example.nepal_app.UI.Fragments.Recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import com.example.nepal_app.Logic.Adaptor.RecyclerViewAdapterDirections;
import com.example.nepal_app.Logic.Adaptor.RecyclerViewAdapterIngrediens;
import com.example.nepal_app.Logic.Factory.RecipeInfo;
import com.example.nepal_app.Logic.Objects.RecipeObj;
import com.example.nepal_app.R;

public class Recipe_fragment extends Fragment {
    private static final String TAG = "RecipeActivity";
    private String mParam1;
    private String mParam2;
    private int position;
    private RecipeInfo recipeInfo;
    private RecipeObj recipeObj;
    private TextView header;
    private ImageView recipeImage;
    private String imageHolder;
    RecyclerViewAdapterIngrediens adapter;
    RecyclerViewAdapterDirections adapter1;
    RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View rod;


    public Recipe_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Saves data into our variables.
        recipeInfo = recipeInfo.getInstance();
        position = recipeInfo.getPostionRecipe();
        recipeObj = recipeInfo.getRecipe(position,getContext());
        imageHolder = recipeInfo.getRecipeImage(position,getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rod = inflater.inflate(R.layout.activity_recipe, container, false);

        header = rod.findViewById(R.id.recipeTitleText);
        recipeImage = rod.findViewById(R.id.recipeImage);

        // Sets recipeImage for individual recipe
        int identifier = getContext().getResources().getIdentifier(imageHolder,"drawable",getContext().getPackageName());
        recipeImage.setImageResource(identifier);

        //sets header
        header.setText(recipeObj.getRecipeName());
        initRecyclerViewIngrediens();
        initRecyclerViewDirections();
        return rod;
    }

    private void initRecyclerViewIngrediens() {
        Log.d(TAG, "initRecyclerViewIngrediens initializing");

        recyclerView = rod.findViewById(R.id.recyclerv_view);
        adapter = new RecyclerViewAdapterIngrediens(getActivity(), recipeObj);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerViewDirections() {
        Log.d(TAG, "initRecyclerViewDirections initializing");

        recyclerView = rod.findViewById(R.id.recyclerv_view1);
        adapter1 = new RecyclerViewAdapterDirections(getActivity(), recipeObj);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter1);
    }
}
