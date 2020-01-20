package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Factory.RecipeInfo;
import com.example.nepal_app.Logic.Objects.RecipeObj;
import com.example.nepal_app.R;

public class RecyclerViewAdapterDirections extends RecyclerView.Adapter<RecyclerViewAdapterDirections.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private RecipeInfo recipeInfo;
    private RecipeObj recipeObj;
    private Context context;

    public RecyclerViewAdapterDirections(Context context, RecipeObj recipeObj) {
        this.recipeObj = recipeObj;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        recipeInfo = RecipeInfo.getInstance();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_directions,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder: called.");

        holder.ingrediensTxt.setText(recipeObj.getDirections(position));
        int num = position +1;
        holder.number.setText(num + ".");
    }

    @Override
    public int getItemCount() {
        return recipeObj.getRecipeArrayListSizeDirections();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingrediensTxt;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            ingrediensTxt = itemView.findViewById(R.id.layoutListItemDirections);
            number = itemView.findViewById(R.id.recipeDirectionsTxtView);
        }
    }
}
