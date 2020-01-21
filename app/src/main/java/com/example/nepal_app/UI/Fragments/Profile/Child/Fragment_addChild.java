package com.example.nepal_app.UI.Fragments.Profile.Child;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nepal_app.Logic.Objects.ChildObj;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class Fragment_addChild extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Button save, pick_date, buttonBack;
    private EditText name;
    private ImageView  preview;
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    private long currentDate;
    private static final int PICK_IMAGE =100;
    private Spinner genders;
    private ChildInfo childInfo;
    private Bitmap bitmap;


    /**
     * Required empty constructor
     */
    public Fragment_addChild() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view2 = inflater.inflate(R.layout.fragment_add_child, container, false);
        save = view2.findViewById(R.id.save_button);
        Button picture = view2.findViewById(R.id.picture);
        name = view2.findViewById(R.id.name);
        pick_date = view2.findViewById(R.id.pickdate_button);
        preview = view2.findViewById(R.id.downloaded_picture);
        preview.setVisibility(View.INVISIBLE);
        genders = view2.findViewById(R.id.gender_spinner);
        pick_date.setOnClickListener(this);
        save.setOnClickListener(this);
        Button deleteButton = view2.findViewById(R.id.button_deleteChild);
        deleteButton.setVisibility(View.INVISIBLE);
        buttonBack = view2.findViewById(R.id.button_editBack);
        buttonBack.setOnClickListener(this);

        childInfo = ChildInfo.getInstance();

        childArr = childInfo.getChildArr(getContext());


        picture.setOnClickListener((view) ->{
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery,PICK_IMAGE);
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        genders.setAdapter(myAdapter);


        return view2;
    }

    /**
     * Getting result inform of picture from the camera roll
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmapTemp;
        float degree;
        Matrix matrix = new Matrix();
        String filePath;
        try {

            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                bitmapTemp = bitmap;
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(imageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
                cursor.close();

                //Rotate image
                if(bitmap.getWidth() > bitmap.getHeight()) {
                    matrix.postRotate(90);
                }

                bitmap = Bitmap.createBitmap(bitmapTemp, 0,0, bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                bitmap = Bitmap.createScaledBitmap(bitmap,200,300,true);


                //Get round image
                Glide.with(this).load(bitmap).
                        apply(RequestOptions.circleCropTransform())
                        .into(preview);

                if (bitmap != null) {
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
            if (String.valueOf(name.getText()).equals("") || currentDate == 0 || bitmap == null ||
                    genders.getSelectedItem().equals("…")) {

                if(childInfo.nameInUse(String.valueOf(name.getText()))){
                    name.setError("Name already in use");
                }
                if (String.valueOf(name.getText()).equals("")){
                    name.setError("Please fill the name of the child");
                }
                if (currentDate == 0) {
                  pick_date.setError("Please pick the birthday of the child");
                }
                if(bitmap == null){
                    Toast.makeText(getContext(),"Add a photo of your child",Toast.LENGTH_LONG).show();
                }
                if(genders.getSelectedItem().equals("…")){
                    Toast.makeText(getContext(),"Select a gender",Toast.LENGTH_LONG).show();
                }
            } else {
                if (childInfo.nameInUse(String.valueOf(name.getText()))){
                    name.setError("Name already in use");
                    return;
                } else  if (childArr.size() != 0) {
                    childArr.add(new ChildObj(String.valueOf(name.getText()), currentDate, String.valueOf(genders.getSelectedItem()),false));
                } else
                    childArr.add(new ChildObj(String.valueOf(name.getText()), currentDate, String.valueOf(genders.getSelectedItem()),true));
                childInfo.setBitmap(bitmap,String.valueOf(name.getText()),getContext());
                childInfo.setChildArr(childArr,getContext());
                //Goes back to the last fragment
                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fm.popBackStack();
            }
        } else if (v == pick_date) {
            showDateDialog();
        } else if (v == buttonBack){
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();
        }
    }


    /**
     *Method to create the date dialog
     */
    private void showDateDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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
        Date date = new Date();
        //Minus because of Java API
        date.setYear(year-1900);
        date.setDate(day);
        date.setMonth(month);
        date.setMinutes(0);
        date.setHours(0);
        date.setSeconds(0);
        if (date.getTime() <= System.currentTimeMillis()) {
            pick_date.setText(childInfo.getMonthText((month + 1)) + " " + day + " " + year);
            currentDate = date.getTime();
        } else {
            Toast.makeText(getContext(),"Not a valid date",Toast.LENGTH_LONG).show();
        }
    }

}


