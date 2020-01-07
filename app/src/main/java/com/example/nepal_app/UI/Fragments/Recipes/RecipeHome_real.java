package com.example.nepal_app.UI.Fragments.Recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Adaptor.RecipeAdapter;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecipeHome_real extends AppCompatActivity implements View.OnClickListener {

    static class allRecipes {

        ArrayList<Integer> recipeImagesRecommended = new ArrayList<>();
        ArrayList<String> recipeNamesRecommended = new ArrayList<>();

        ArrayList<Integer> recipeImagesSnacks = new ArrayList<>();
        ArrayList<String> recipeNamesSnacks = new ArrayList<>();

        ArrayList<Integer> recipeImagesCommon = new ArrayList<>();
        ArrayList<String> recipeNamesCommon = new ArrayList<>();

        public void fillLists() {

            recipeImagesRecommended.add(R.drawable.recipehome_bananas);
            recipeImagesRecommended.add(R.drawable.recipehome_cake);
            recipeImagesRecommended.add(R.drawable.recipehome_dal);
            recipeImagesRecommended.add(R.drawable.recipehome_chicken);

            recipeNamesRecommended.add("Banana");
            recipeNamesRecommended.add("Cake");
            recipeNamesRecommended.add("Dal");
            recipeNamesRecommended.add("Chicken");

            recipeImagesSnacks.add(R.drawable.recipehome_bananas);
            recipeImagesSnacks.add(R.drawable.recipehome_cake);
            recipeImagesSnacks.add(R.drawable.recipehome_dal);
            recipeImagesSnacks.add(R.drawable.recipehome_chicken);

            recipeNamesSnacks.add("Banana");
            recipeNamesSnacks.add("Cake");
            recipeNamesSnacks.add("Dal");
            recipeNamesSnacks.add("Chicken");

            recipeImagesCommon.add(R.drawable.recipehome_bananas);
            recipeImagesCommon.add(R.drawable.recipehome_cake);
            recipeImagesCommon.add(R.drawable.recipehome_dal);
            recipeImagesCommon.add(R.drawable.recipehome_chicken);

            recipeNamesCommon.add("Banana");
            recipeNamesCommon.add("Cake");
            recipeNamesCommon.add("Dal");
            recipeNamesCommon.add("Chicken");
        }

    }

    allRecipes data = new allRecipes();

    HashSet<Integer> opened = new HashSet<>();

    RecyclerView recyclerView;

    Button btnRec, btnFav, btnSna, btnCom, btnSea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data.fillLists();

        btnRec = findViewById(R.id.btnRecommended);
        btnFav = findViewById(R.id.btnFavorites);
        btnSna = findViewById(R.id.btnSnacks);
        btnCom = findViewById(R.id.btnCommon);
        btnSea = findViewById(R.id.btnSearch);

        btnRec.setOnClickListener(this);
        btnFav.setOnClickListener(this);
        btnSna.setOnClickListener(this);
        btnCom.setOnClickListener(this);
        btnSea.setOnClickListener(this);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        setContentView(recyclerView);
    }

    @Override
    public void onClick(View v) {

        if (v == btnRec) {

        }
        if (v == btnFav) {

        }
        if (v == btnSna) {

        }
        if (v == btnCom) {

        }
        if (v == btnSea) {

        }

    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<EkspanderbartListeelemViewholder>() {

        @Override
        public int getItemCount()  {
            return data.recipeImagesRecommended.size();
        }

        @Override
        public EkspanderbartListeelemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout rodLayout = new LinearLayout(parent.getContext());
            rodLayout.setOrientation(LinearLayout.VERTICAL);
            EkspanderbartListeelemViewholder vh = new EkspanderbartListeelemViewholder(rodLayout);
            vh.rodLayout = rodLayout;
            vh.theview = getLayoutInflater().inflate(R.layout.recipe_home_real_layout, parent, false);
            vh.image = vh.theview.findViewById(R.id.name);
            vh.åbnLukBillede = vh.landeview.findViewById(R.id.image);
            vh.landeview.setOnClickListener(vh);
            vh.landeview.setBackgroundResource(android.R.drawable.list_selector_background); // giv visuelt feedback når der trykkes på baggrunden
            vh.åbnLukBillede.setOnClickListener(vh);
//      vh.åbnLukBillede.setBackgroundResource(android.R.drawable.btn_default);
            vh.rodLayout.addView(vh.landeview);
            return vh;
        }

        @Override
        public void onBindViewHolder(EkspanderbartListeelemViewholder vh, int position) {
            boolean åben = åbneLande.contains(position);
            vh.overskrift.setText(data.lande.get(position) +" åben="+åben);
            vh.beskrivelse.setText("Land nummer " + position + " på vh@"+Integer.toHexString(vh.hashCode()));

            if (!åben) {
                vh.åbnLukBillede.setImageResource(android.R.drawable.ic_input_add); // vis 'åbn' ikon
                for (View underview : vh.underviews) underview.setVisibility(View.GONE); // skjul underelementer
            } else {
                vh.åbnLukBillede.setImageResource(android.R.drawable.ic_delete); // vis 'luk' ikon

                List<String> byerILandet = data.byer.get(position);

                while (vh.underviews.size()<byerILandet.size()) { // sørg for at der er nok underviews
                    TextView underView = new TextView(vh.rodLayout.getContext());
                    //underView.setPadding(0, 20, 0, 20);
                    underView.setBackgroundResource(android.R.drawable.list_selector_background);
                    underView.setOnClickListener(vh);      // lad viewholderen håndtere evt klik
                    underView.setId(vh.underviews.size()); // unik ID så vi senere kan se hvilket af underviewne der klikkes på
                    vh.rodLayout.addView(underView);
                    vh.underviews.add(underView);
                }

                for (int i=0; i<vh.underviews.size(); i++) { // sæt underviews til at vise det rigtige indhold
                    TextView underView = vh.underviews.get(i);
                    if (i<byerILandet.size()) {
                        underView.setText(byerILandet.get(i));
                        underView.setVisibility(View.VISIBLE);
                    } else {
                        underView.setVisibility(View.GONE);      // for underviewet skal ikke bruges
                    }
                }
            }
        }
    };


    /**
     * En Viewholder husker forskellige views i et listeelement, sådan at søgninger i viewhierakiet
     * med findViewById() kun behøver at ske EN gang.
     * Se https://developer.android.com/training/material/lists-cards.html
     */
    class EkspanderbartListeelemViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout rodLayout;
        TextView name;
        ImageView image;
        View theview;
        ArrayList<TextView> underviews = new ArrayList<>();

        public EkspanderbartListeelemViewholder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

            if (v == åbnLukBillede || v==landeview) { // Klik på billede åbner/lukker for listen af byer i dette land
                boolean åben = åbneLande.contains(position);
                if (åben) åbneLande.remove(position); // luk
                else åbneLande.add(position); // åbn
                adapter.notifyItemChanged(position);
            } else {
                int id = v.getId();
                Toast.makeText(v.getContext(), "Klik på by nummer " + id + " i "+data.lande.get(position), Toast.LENGTH_SHORT).show();
            }
        }
    }
}