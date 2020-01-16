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
import com.example.nepal_app.Logic.Objects.RecipeObj;
import com.example.nepal_app.R;

public class RecyclerViewAdapterIngrediens extends RecyclerView.Adapter<RecyclerViewAdapterIngrediens.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private RecipeInfo recipeInfo;
    private RecipeObj recipeObj;
    private Context context;

    public RecyclerViewAdapterIngrediens(Context context, RecipeObj recipeObj) {
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
        holder.ingrediensTxt.setText(recipeObj.getIngrediens(position));
        int identifier = context.getResources().getIdentifier(recipeObj.getImages(position),"drawable",context.getPackageName());
        holder.ingrediensImage.setImageResource(identifier);
    }

    @Override
    public int getItemCount() {
        return recipeObj.getRecipeArrayListSizeIngrediens();
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

