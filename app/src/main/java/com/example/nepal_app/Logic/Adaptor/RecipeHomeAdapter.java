package com.example.nepal_app.Logic.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Factory.RecipeInfo;
import com.example.nepal_app.Logic.RecipeHomeObject;
import com.example.nepal_app.MainActivity;
import com.example.nepal_app.R;
import com.example.nepal_app.UI.Fragments.Recipes.Recipe_fragment;

import java.util.List;

public class RecipeHomeAdapter extends RecyclerView.Adapter<RecipeHomeAdapter.recipelistVH>{
    List<RecipeHomeObject> recipeList;
    private Context context;
    private RecipeInfo recipeInfo;

    public RecipeHomeAdapter(List<RecipeHomeObject> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public recipelistVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_home_recipe, parent, false);
        context = parent.getContext();
        recipeInfo = RecipeInfo.getInstance();
        return new recipelistVH(v);
    }

    @Override
    public void onBindViewHolder(recipelistVH holder, int position) {
            int identifier = context.getResources().getIdentifier(recipeList.get(position).getRecipeImg(),"drawable",context.getPackageName());
            holder.recImg.setImageResource(identifier);
            holder.recName.setText(recipeList.get(position).getRecipeName());
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position < recipeList.size()) {
                        recipeInfo.setPostionRecipe(position);
                        Fragment recipeFragment = new Recipe_fragment();
                        MainActivity mainActivity = (MainActivity) context;
                        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,recipeFragment);
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

        public recipelistVH(View itemView) {
            super(itemView);

            recName = itemView.findViewById(R.id.recipeName2);
            recImg = itemView.findViewById(R.id.recipeImg2);
            btn = itemView.findViewById(R.id.recipeBtnView2);

        }
    }
}
