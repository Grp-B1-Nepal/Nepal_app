package com.example.nepal_app.fragments.child;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.nepal_app.Factory.POJO;
import com.example.nepal_app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


import static android.app.Activity.RESULT_OK;


public class Fragment_addChild extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Button save, pick_date;
    private EditText name;
    private ImageView  preview;
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    private ArrayList<Uri> imageArr = new ArrayList<>();
    private ArrayList<String> imageStringArr = new ArrayList<>();
    private long currentDate;
    private static final int PICK_IMAGE =100;
    private Spinner genders;
    private Uri imageUri = null;
    private POJO pojo;
    private ConstraintLayout picture;



    public Fragment_addChild() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_add_child, container, false);
        save = view2.findViewById(R.id.save_button);
        picture = view2.findViewById(R.id.billede);
        name = view2.findViewById(R.id.name);
        pick_date = view2.findViewById(R.id.pickdate_button);
        preview = view2.findViewById(R.id.downloaded_picture);
        preview.setVisibility(View.INVISIBLE);
        genders = view2.findViewById(R.id.gender_spinner);
        pick_date.setOnClickListener(this);
        save.setOnClickListener(this);

        pojo = POJO.getInstance();

        childArr = pojo.getChildArr(getContext());


        picture.setOnClickListener((view) ->{
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery,PICK_IMAGE);
        });



        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genders.setAdapter(myAdapter);

        

        return view2;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
//TODO FIND A WAY TO SAVE THE PICTURE IN A CACHE
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                preview.setImageURI(imageUri);
                imageArr.add(imageUri);
                pojo.setUri(imageArr);
                if (imageUri != null) {
                    preview.setVisibility(View.VISIBLE);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        
    @Override
    public void onClick(View v) {

        if (v == save){
            if (String.valueOf(name.getText()).equals("")){
                name.setError("Please fill the name of the child");
            } else if (currentDate == 0) {
                pick_date.setError("Please pick the birthday of the child");
            //} else if(imageUri == null){
              //  Toast.makeText(getContext(),"Add a photo of your child",Toast.LENGTH_LONG).show();
            } else if(genders.getSelectedItem().equals("…")){
                Toast.makeText(getContext(),"Select a gender",Toast.LENGTH_LONG).show();
            } else {
                childArr.add(new ChildObj(String.valueOf(name.getText()), currentDate, String.valueOf(genders.getSelectedItem())));

                saveChild();
                saveImage();
                //Goes back to the last fragment
                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fm.popBackStack();
            }
        }

        if (v == pick_date) {
            showDateDialog();
        }
    }


    /**
     *Method to create the date dialog
     */
    private void showDateDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
        }


    @Override
    /**
     * Implements the onDateSet from the DatePickerDialog to get tha data picked date from the calendar
     *
     */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        pick_date.setText(day + "/" + (month+1) + "/" + year);
        currentDate = c.getTimeInMillis();
    }

    /**
     * Saving the child in the cache
     */
    private void saveChild(){
    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Children", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    Gson gson = new Gson();
    String json = gson.toJson(childArr);
    editor.putString("ChildArr",json);
    editor.apply();
    pojo.setChildArr(childArr);

    }


    private void saveImage(){
        if (pojo.getURI(getContext()).size() != 0) {
            for (int i = 0; i < pojo.getURI(getContext()).size(); i++) {
                imageArr.add(Uri.parse(pojo.getURI(getContext()).get(i)));
            }
        }
        if (imageArr.size() != 0){
            for (int i = 0; i < imageArr.size(); i++) {
                imageStringArr.add(String.valueOf(imageArr.get(i)));
            }
        }

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(imageStringArr);
        editor.putString("Uri_image", json);
        editor.apply();
        pojo.setUri(imageArr);

        editor.apply();
    }
}


