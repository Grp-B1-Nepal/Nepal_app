package com.example.nepal_app.Fragments;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    ArrayList<Integer> imageViews;
    ArrayList<String> recipeNames;
    private OnNoteListener mOnNoteListener;

    public RecipeAdapter(ArrayList<Integer> imageViews, ArrayList<String> recipeNames, OnNoteListener onNoteListener) {

        this.imageViews = imageViews;
        this.recipeNames = recipeNames;
        this.mOnNoteListener = onNoteListener;

    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_listhelper, parent, false);
            ViewHolder vh = new ViewHolder(view, mOnNoteListener);

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
            return imageViews.size();
        }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public ImageView recipeImage;
        public TextView recipeName;
        OnNoteListener onNoteListener;

        public ViewHolder(View itemView, OnNoteListener OnNoteListner) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.imageofrecipe);
            recipeName = itemView.findViewById(R.id.nameofrecipe);

            this.onNoteListener = mOnNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}



