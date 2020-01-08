package com.example.nepal_app.Logic.Adaptor;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.RecipeForHome;
import com.example.nepal_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.RecipeVH>{

    private static final String TAG = "RecipeHomeAdapter";
    List<RecipeForHome> recipeList;
    ArrayList<String> categoryList = new ArrayList<>();

    public void fillCatList() {
        categoryList.add("Recommended");
        categoryList.add("Favorites");
        categoryList.add("Snacks");
        categoryList.add("Common");
        categoryList.add("Search");
    }

    public RecipeHomeAdapter(List<RecipeForHome> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public RecipeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_tester, parent, false);
        fillCatList();
        return new RecipeVH(v);
    }

    @Override
    public void onBindViewHolder(RecipeVH holder, int position) {

        RecipeForHome recipe = recipeList.get(position);
        holder.btnCategory.setText(categoryList.get(position));

        boolean isExpanded = recipeList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeVH extends RecyclerView.ViewHolder{

        private static final String TAG = "RecipeVH";

        RecyclerView expandableLayout;
        TextView recName;
        ImageView recImg;
        Button btnCategory;

        public RecipeVH(View itemView) {
            super(itemView);

            btnCategory = itemView.findViewById(R.id.btnCategory);
            expandableLayout = itemView.findViewById(R.id.recView);


            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RecipeForHome recipe = recipeList.get(getAdapterPosition());
                    recipe.setExpanded(!recipe.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }

    }
}
