package com.example.nepal_app.Fragments.child;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nepal_app.Factory.POJO;
import com.example.nepal_app.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class EditChild extends Fragment implements View.OnClickListener {
    private int position;
    private TextView textName, textGender, textBirthday;
    private Button buttonBirthday, buttonImage, buttonBack, buttonSave, buttonDelete;
    private EditText editName;
    private Bitmap   editBitmap;
    private String name, gender, birthday, oldName;
    private ArrayList<ChildObj> arr = new ArrayList<>();
    private long newBirthday;
    private POJO pojo;
    private static final int PICK_IMAGE =100;
    private Uri imageUri = null;
    private ImageView image;
    private Spinner genders;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_edit_child, container, false);

        pojo = POJO.getInstance();

        position = pojo.getPosition();
        textName = view2.findViewById(R.id.textView_editName);
        textGender = view2.findViewById(R.id.textView_editGender);
        textBirthday = view2.findViewById(R.id.textView_editBirthday);


        editName = view2.findViewById(R.id.editText_editName);
        genders = view2.findViewById(R.id.gender_editSpinner);

        buttonBirthday = view2.findViewById(R.id.button_newBirthday);
        buttonImage = view2.findViewById(R.id.button_editImage);
        buttonBack = view2.findViewById(R.id.button_editBack);
        buttonSave = view2.findViewById(R.id.button_editSave);
        buttonDelete = view2.findViewById(R.id.button_deleteChild);

        buttonDelete.setOnClickListener(this);
        buttonBirthday.setOnClickListener(this);
        buttonImage.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


        image = view2.findViewById(R.id.image_editView);
        image.setVisibility(View.INVISIBLE);
        arr = pojo.getChildArr(getContext());

        arr = pojo.getChildArr(getContext());
        oldName = arr.get(position).getName();
        name = arr.get(position).getName();
        gender = arr.get(position).getGender();
        birthday = getBirthday(position);
        newBirthday = arr.get(position).getBirthday();


        textName.setText("The current name is: " + name);
        textBirthday.setText("The current birthday is: " + birthday);
        textGender.setText("The current gender is: " + gender);
        buttonImage.setText("Click to  update the picture of " + name);
        buttonImage.setText("Set new image of " + name);
        buttonDelete.setText("Delete " + name);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genders.setAdapter(myAdapter);
        // Inflate the layout for this fragment
        return view2;
    }


    @Override
    public void onClick(View v) {
        if (v == buttonSave) {
            if (!(String.valueOf(editName.getText()).equals(""))) {
            name = String.valueOf(editName.getText());
            arr.get(position).setName(name);
            pojo.newNameImage(getContext(),oldName,name);
        }
        if (!(genders.getSelectedItem().equals("…"))) {
            gender = String.valueOf(genders.getSelectedItem());
            arr.get(position).setGender(gender);
        }
        if (editBitmap != null){
            saveImage();
        }

        saveChild();

        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        fm.popBackStack();

        } else if (v == buttonBirthday){
            showDateDialog();
        } else if (v == buttonImage){
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery,PICK_IMAGE);

        } else if (v == buttonBack){
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();
        } else if (v == buttonDelete){
            pojo.deleteChild(position,getContext());
            arr.remove(position);
            saveChild();
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();
        }
    }


    /**
     *Method to create the date dialog
     */
    private void showDateDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                this::onDateSet,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        buttonBirthday.setText(day + "/" + (month+1) + "/" + year);
        newBirthday = c.getTimeInMillis();
        arr.get(position).setBirthday(newBirthday);
    }

    private String getBirthday(int index) {
        Calendar calendar = Calendar.getInstance();
        String date1, date2, date3;
            calendar.setTimeInMillis(arr.get(index).getBirthday());
            date1 = calendar.getTime().toString().substring(4, 10);
            date2 = calendar.getTime().toString().substring(30, 34);
            date3 = "Birthday " + date1 + " " + date2;
            return date3;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {

            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                editBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                image.setImageBitmap(editBitmap);
                if (image != null){
                    image.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveChild(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Children", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("ChildArr",json);
        editor.apply();
        pojo.setChildArr(arr);

    }

    private void saveImage(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(name).apply();


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        editBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArr = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteArr,Base64.DEFAULT);

        editor.putString(name, encodeImage);
        editor.apply();
    }


}
