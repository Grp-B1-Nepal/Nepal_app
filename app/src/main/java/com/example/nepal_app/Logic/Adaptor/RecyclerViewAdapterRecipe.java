package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.R;

import java.util.ArrayList;

public class RecyclerViewAdapterRecipe extends RecyclerView.Adapter<RecyclerViewAdapterRecipe.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Integer> foodImage;
    private ArrayList<String> foodName;
    private Context context;

    public RecyclerViewAdapterRecipe(Context context, ArrayList<Integer> foodImage, ArrayList<String> foodName) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder: called.");

        holder.foodName.setText(foodName.get(position));
        holder.foodImage.setImageResource(foodImage.get(position));
    }

    @Override
    public int getItemCount() {
        return foodImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;

        public ViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.layoutListItemImage);
            foodName = itemView.findViewById(R.id.layoutListItemFoodName);

        }
    }
}

