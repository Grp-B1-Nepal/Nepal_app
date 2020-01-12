package com.example.nepal_app.Logic.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.RecipeObject;
import com.example.nepal_app.R;

import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.recipelistVH>{
    List<RecipeObject> recipeList;

    public RecipeHomeAdapter(List<RecipeObject> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public recipelistVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_recipe, parent, false);
        return new recipelistVH(v);
    }

    @Override
    public void onBindViewHolder(recipelistVH holder, int position) {

            holder.recImg.setImageResource(recipeList.get(position).getRecipeImg());
            holder.recName.setText(recipeList.get(position).getRecipeName());

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class recipelistVH extends RecyclerView.ViewHolder {

        TextView recName;
        ImageView recImg;

        public recipelistVH(View itemView) {
            super(itemView);

            recName = itemView.findViewById(R.id.recipeName2);
            recImg = itemView.findViewById(R.id.recipeImg2);
        }

    }


}
