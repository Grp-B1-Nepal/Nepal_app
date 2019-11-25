package com.example.nepal_app.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nepal_app.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class Fragment_addChild extends Fragment {
    Button button2;
    EditText name, birthday, gender;
    ImageView imageView12, imageViewPreview, preview;
    ConstraintLayout billede;

    //Gallery select variables
    public static final int GALLERY_SELECT = 1887;

    //Upload variables
    String ImageString = "flexicu";
    Bitmap ImageData = null;
    Uri imageUri = null;

    public Fragment_addChild() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_add_child, container, false);
        button2 = view2.findViewById(R.id.button2);
        billede = view2.findViewById(R.id.billede);
        name = view2.findViewById(R.id.name);
        birthday = view2.findViewById(R.id.birthday);
        gender = view2.findViewById(R.id.gender);
        imageView12 = view2.findViewById(R.id.imageView12);
        preview = view2.findViewById(R.id.imageViewPreview);
        preview.setVisibility(View.INVISIBLE);


        //If you don't have the permission to open the Gallery ask for them, otherwise open the gallery
        billede.setOnClickListener((view) ->{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_SELECT);
        });



        return view2;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo = null;
        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                photo = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageData = photo;
        }


    }


        }
