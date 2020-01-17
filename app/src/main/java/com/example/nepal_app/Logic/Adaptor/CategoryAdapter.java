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
    List<RecipeHomeObject> recipeListFull;
    List<Integer> btnIcons;
    Context context;

    public CategoryAdapter(List<CategoryObject> categoryList, List<Integer> btnIcons, Context context) {
        this.categoryList = categoryList;
        this.btnIcons = btnIcons;
        this.context = context;
        recipeListFull = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            recipeListFull.addAll(categoryList.get(i).getRecipeList());
        }

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
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RecipeHomeObject> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (RecipeHomeObject item : recipeListFull) {
                    if (item.getRecipeName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            for (int i = 0; i < categoryList.size(); i++) {
                categoryList.get(i).getRecipeList().clear();
                categoryList.get(i).setRecipeList((List)results.values);
                notifyDataSetChanged();
            }
        }
    };
}
