package com.example.nepal_app.Logic.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.R;
import com.example.nepal_app.UI.Fragments.Recipes.RecipeHome;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.ViewHolder> {

    String[] category;

    public RecipeHomeAdapter(String[] category) {
        this.category = category;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO FIX LAYOUT
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_real_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(category[position]);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            //TODO FIX ID REFERENCE
            categoryName = itemView.findViewById(R.id.name);

        }

    }
}
