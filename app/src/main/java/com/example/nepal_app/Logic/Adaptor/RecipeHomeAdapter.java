package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Factory.RecipeInfo;
import com.example.nepal_app.Logic.FavoriteRecipes;
import com.example.nepal_app.Logic.Objects.RecipeHomeObject;

import com.example.nepal_app.MainActivity;
import com.example.nepal_app.R;
import com.example.nepal_app.UI.Fragments.Recipes.Recipe_fragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.recipelistVH>{
    List<RecipeHomeObject> recipeList;
    private Context context;
    private RecipeInfo recipeInfo;


    public RecipeHomeAdapter(List<RecipeHomeObject> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @Override
    public recipelistVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recipe_home_recipe, parent, false);
        recipeInfo = RecipeInfo.getInstance();

        return new recipelistVH(v);
    }


    @Override
    public void onBindViewHolder(recipelistVH holder, int position) {

        int identifier = context.getResources().getIdentifier(recipeList.get(position).getRecipeImg(), "drawable", context.getPackageName());
        holder.recImg.setImageResource(identifier);
        holder.recName.setText(recipeList.get(position).getRecipeName());
        recipeInfo = recipeInfo.getInstance();
        recipeInfo.setRecipeName(recipeList.get(position).getRecipeName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < recipeList.size()) {
                    Fragment recipeFragment = new Recipe_fragment();
                    MainActivity mainActivity = (MainActivity) context;
                    FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, recipeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                 }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class recipelistVH extends RecyclerView.ViewHolder {

        TextView recName;
        ImageView recImg;
        Button btn;
        ToggleButton fav;

        public recipelistVH(View itemView) {
            super(itemView);

            recName = itemView.findViewById(R.id.recipeName2);
            recImg = itemView.findViewById(R.id.recipeImg2);
            btn = itemView.findViewById(R.id.recipeBtnView2);
            fav = itemView.findViewById(R.id.recipeBtnLike2);

            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        String recipeName = recipeInfo.getRecipeName();
                        int position = recipeInfo.getRecipePosition(context,recipeName);
                        fav.setBackgroundResource(R.drawable.favorite_filled_foreground);
                        addToFavoriteArray(position);
                        notifyDataSetChanged();
                    } else {
                        fav.setBackgroundResource(R.drawable.favorite_empty_foreground);
                        removeFromFavoriteArray(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });

        }

        public void addToFavoriteArray(int position) {
            RecipeHomeObject recipe = recipeInfo.getSingleHomeRecipe(position, context);
            FavoriteRecipes.getInstance().favoriteList.add(recipe);

        }

        public void removeFromFavoriteArray(int position) {
            ArrayList<RecipeHomeObject> listTemp = FavoriteRecipes.getInstance().favoriteList;
            ArrayList<String> listTempNames = new ArrayList<>();
            for (int i = 0; i < listTemp.size(); i++) {
                listTempNames.add(listTemp.get(i).getRecipeName());
            }
            RecipeHomeObject recipe = recipeInfo.getSingleHomeRecipe(position, context);
            int index = listTempNames.indexOf(recipe.getRecipeName());
            FavoriteRecipes.getInstance().favoriteList.remove(index);
        }
    }
}
