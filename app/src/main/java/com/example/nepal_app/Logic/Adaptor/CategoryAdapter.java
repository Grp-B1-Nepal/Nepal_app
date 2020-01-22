package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Objects.CategoryObject;
import com.example.nepal_app.Logic.Objects.RecipeHomeObject;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryVH> implements Filterable{
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    List<CategoryObject> categoryList;
    List<RecipeHomeObject> recipeListRecFull, recipeListSnackFull, recipeListCommonFull; //This is for the search filter. We need to know what a full list looks like
    List<Integer> btnIcons;
    Context context;

    public CategoryAdapter(List<CategoryObject> categoryList, List<Integer> btnIcons, Context context) {
        this.categoryList = categoryList;
        this.btnIcons = btnIcons;
        this.context = context;
        recipeListRecFull = new ArrayList<>();
        recipeListSnackFull = new ArrayList<>();
        recipeListCommonFull = new ArrayList<>();
        //This is for the search filter. We need to know what a full list looks like
        recipeListRecFull.addAll(categoryList.get(0).getRecipeList());
        recipeListSnackFull.addAll(categoryList.get(1).getRecipeList());
        recipeListCommonFull.addAll(categoryList.get(2).getRecipeList());

    }

    @Override
    public categoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_category, parent, false);
        return new categoryVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryVH holder, int position) {
        holder.category.setText(categoryList.get(position).getCategoryName());
        holder.category.setCompoundDrawablesRelativeWithIntrinsicBounds(0, btnIcons.get(position), 0, 0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvRecipe.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(categoryList.get(position).getRecipeList().size());

        RecipeHomeAdapter recipeAdapter = new RecipeHomeAdapter(categoryList.get(position).getRecipeList(), context);

        holder.rvRecipe.setLayoutManager(layoutManager);
        holder.rvRecipe.setAdapter(recipeAdapter);
        holder.rvRecipe.setRecycledViewPool(viewPool);

        //Here we check whether or not a category has been clicked on and is expanded
        boolean isExpanded = categoryList.get(position).isExpanded();
        holder.rvRecipe.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class categoryVH extends RecyclerView.ViewHolder {
        Button category;
        RecyclerView rvRecipe;

        public categoryVH(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.btnCategory);
            rvRecipe = itemView.findViewById(R.id.recviewRecipe);


            category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //We expand the list of recipes that matches the category pressed
                    CategoryObject cat = categoryList.get(getAdapterPosition());
                    cat.setExpanded(!cat.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }

    @Override
    public Filter getFilter() {
        return theFilter;
    }

    Filter theFilter = new Filter() {


        //This is our search filter, were we filter the recipes, based on what was typed
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //We create the filtered lists. constraint = what the user typed in
            List<RecipeHomeObject> filteredListRec = new ArrayList<>();
            List<RecipeHomeObject> filteredListSnack = new ArrayList<>();
            List<RecipeHomeObject> filteredListCom = new ArrayList<>();

            //If there is nothing typed in, we show all the recipes
            if (constraint == null || constraint.length() == 0) {
                filteredListRec.addAll(recipeListRecFull);
                filteredListSnack.addAll(recipeListSnackFull);
                filteredListCom.addAll(recipeListCommonFull);
            } else {
                //the filterpattern is what we type in, all in lower case with no whitespacces
                String filterPattern = constraint.toString().toLowerCase().trim();

                //We look through all lists and see if they contain a recipeName, that matches the filter pattern
                for (RecipeHomeObject item : recipeListRecFull) {
                    if (item.getRecipeName().toLowerCase().contains(filterPattern)) {
                        filteredListRec.add(item);
                        notifyDataSetChanged();
                    }
                }
                for (RecipeHomeObject item : recipeListSnackFull) {
                    if (item.getRecipeName().toLowerCase().contains(filterPattern)) {
                        filteredListSnack.add(item);
                        notifyDataSetChanged();
                    }
                }
                for (RecipeHomeObject item : recipeListCommonFull) {
                    if (item.getRecipeName().toLowerCase().contains(filterPattern)) {
                        filteredListCom.add(item);
                        notifyDataSetChanged();
                    }
                }
            }

            //We create filter results, which is the recipes that matches. We add all matches to a single arraylist, and make the results equal that list
            FilterResults results = new FilterResults();
            ArrayList<ArrayList<RecipeHomeObject>> filteredListAll = new ArrayList<>();
            filteredListAll.add((ArrayList<RecipeHomeObject>) filteredListRec);
            filteredListAll.add((ArrayList<RecipeHomeObject>) filteredListSnack);
            filteredListAll.add((ArrayList<RecipeHomeObject>) filteredListCom);

            results.values = filteredListAll;
            return results;
        }

        //This method then chooses what to display, based on the results
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            categoryList.get(0).getRecipeList().clear();
            notifyDataSetChanged();
            categoryList.get(0).setRecipeList((List)((List)results.values).get(0));
            notifyDataSetChanged();

            categoryList.get(1).getRecipeList().clear();
            notifyDataSetChanged();
            categoryList.get(1).setRecipeList((List)((List) results.values).get(1));
            notifyDataSetChanged();

            categoryList.get(2).getRecipeList().clear();
            notifyDataSetChanged();
            categoryList.get(2).setRecipeList((List)((List) results.values).get(2));
            notifyDataSetChanged();

        }
    };
}
