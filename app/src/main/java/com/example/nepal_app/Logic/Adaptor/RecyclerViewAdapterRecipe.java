package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Factory.RecipeInfo;
import com.example.nepal_app.Logic.RecipeObj;
import com.example.nepal_app.R;

public class RecyclerViewAdapterRecipe extends RecyclerView.Adapter<RecyclerViewAdapterRecipe.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private RecipeInfo recipeInfo;
    private RecipeObj recipeObj;
    private Context context;

    public RecyclerViewAdapterRecipe(Context context, RecipeObj recipeObj) {
        this.recipeObj = recipeObj;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        recipeInfo = RecipeInfo.getInstance();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder: called.");

        if(position == recipeObj.getRecipeArrayListSize() + 1) {
            holder.ingrediensImage.setVisibility(View.INVISIBLE);
            String str = "";
            for (int i = 0; i < recipeObj.getDirections().size(); i++) {
                str = recipeObj.getDirections().get(i) + "\n";
                str = str.concat(str);
            }
            holder.ingrediensTxt.setText(str);
        } else {
            holder.ingrediensTxt.setText(recipeObj.getIngrediens(position));
            holder.ingrediensImage.setImageResource(recipeObj.getImages(position));
        }
    }

    @Override
    public int getItemCount() {
        return recipeObj.getRecipeArrayListSize() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ingrediensImage;
        TextView ingrediensTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            ingrediensImage = itemView.findViewById(R.id.layoutListItemImage);
            ingrediensTxt = itemView.findViewById(R.id.layoutListItemFoodName);
        }
    }
}

