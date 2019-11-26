package com.example.nepal_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    ArrayList<Integer> imageViews;
    ArrayList<String> recipeNames;

    public RecipeAdapter(ArrayList<Integer> imageViews, ArrayList<String> recipeNames) {

        this.imageViews = imageViews;
        this.recipeNames = recipeNames;

    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciperetry, parent, false);
            ViewHolder vh = new ViewHolder(view);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //images
            holder.recipeImage.setImageResource(imageViews.get(position));

            //texts
            holder.recipeName.setText(recipeNames.get(position));


        }

        @Override
        public int getItemCount() {
            //should return size of arraylist
            return imageViews.size();
        }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView recipeImage;
        public TextView recipeName;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.imageofrecipe);
            recipeName = itemView.findViewById(R.id.nameofrecipe);
        }
    }
}



