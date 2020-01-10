package com.example.nepal_app.Logic.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.CategoriesWithRecipeListsObject;
import com.example.nepal_app.Logic.RecipeForHome;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.RecipeVH>{

    private static final String TAG = "RecipeHomeAdapter";
    List<CategoriesWithRecipeListsObject> allRecAndCatList;

    public RecipeHomeAdapter(List<CategoriesWithRecipeListsObject> allRecAndCatList) {
        this.allRecAndCatList = allRecAndCatList;
    }

    @Override
    public RecipeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_info_to_display, parent, false);
        return new RecipeVH(v);
    }

    @Override
    public void onBindViewHolder(RecipeVH holder, int position) {

        holder.btnCategory.setText(allRecAndCatList.get(position).getCategory());
        ArrayList<RecipeForHome> recipe = allRecAndCatList.get(position).getRecipeList();
        holder.recImg.setImageResource(recipe.get(position).getRecipeImg());
        holder.recName.setText(recipe.get(position).getRecipeName());

        boolean isExpanded = allRecAndCatList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return allRecAndCatList.size();
    }

    public class RecipeVH extends RecyclerView.ViewHolder{

        private static final String TAG = "RecipeVH";

        ConstraintLayout expandableLayout;
        TextView recName;
        ImageView recImg;
        Button btnCategory;

        public RecipeVH(View itemView) {
            super(itemView);

            btnCategory = itemView.findViewById(R.id.btnCategory);
            recImg = itemView.findViewById(R.id.recipeImg);
            recName = itemView.findViewById(R.id.recipeName);
            expandableLayout = itemView.findViewById(R.id.expandLay);

            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CategoriesWithRecipeListsObject catAndRecipe = allRecAndCatList.get(getAdapterPosition());
                    catAndRecipe.setExpanded(!catAndRecipe.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }

    }
}
