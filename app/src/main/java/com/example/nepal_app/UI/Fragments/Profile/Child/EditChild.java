package com.example.nepal_app.UI.Fragments.Profile.Child;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nepal_app.Logic.ChildObj;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class EditChild extends Fragment implements View.OnClickListener {
    private int position;
    private Button buttonBirthday, buttonBack, buttonSave, buttonDelete;
    private EditText editName;
    private Bitmap   editBitmap;
    private String name, gender, birthday, oldName;
    private ArrayList<ChildObj> arr = new ArrayList<>();
    private ChildInfo childInfo;
    private static final int PICK_IMAGE =100;
    private Uri imageUri = null;
    private ImageView image;
    private ConstraintLayout buttonImage;
    private Spinner genders;
    private Date childDate = new Date();
    private int year,month,day;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_add_child, container, false);

        childInfo = ChildInfo.getInstance();

        position = childInfo.getPosition();
        editName = view2.findViewById(R.id.name);


        genders = view2.findViewById(R.id.gender_spinner);

        buttonBirthday = view2.findViewById(R.id.pickdate_button);
        buttonImage = view2.findViewById(R.id.picture);
        buttonBack = view2.findViewById(R.id.button_editBack);
        buttonSave = view2.findViewById(R.id.save_button);
        buttonDelete = view2.findViewById(R.id.button_deleteChild);

        buttonDelete.setOnClickListener(this);
        buttonBirthday.setOnClickListener(this);
        buttonImage.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        image = view2.findViewById(R.id.downloaded_picture);
        image.setVisibility(View.VISIBLE);


        arr = childInfo.getChildArr(getContext());
        //Gets the objects from the ChillObj in the arr
        oldName = arr.get(position).getName();
        name = arr.get(position).getName();
        gender = arr.get(position).getGender();
        birthday = getBirthday(position);

        Glide.with(this).load(childInfo.getBitmap(getContext(),name)).
                apply(RequestOptions.circleCropTransform())
                .into(image);

        //Sets up known info
        image.setImageBitmap(childInfo.getBitmap(getContext(),name));
        editName.setText(name);
        buttonBirthday.setText(birthday);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        genders.setAdapter(myAdapter);

        // Inflate the layout for this fragment
        return view2;
    }


    @Override
    public void onClick(View v) {
        if (buttonSave.equals(v)) {
            if (!(String.valueOf(editName.getText()).equals(""))) {
                name = String.valueOf(editName.getText());
                arr.get(position).setName(name);
                childInfo.newNameImage(getContext(), oldName, name);
            }
            if (!(genders.getSelectedItem().equals("…"))) {
                gender = String.valueOf(genders.getSelectedItem());
                arr.get(position).setGender(gender);
            }
            if (editBitmap != null) {
                childInfo.setBitmap(editBitmap, name, getContext());
            }
            childInfo.setChildArr(arr, getContext());
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();

        } else if (buttonBirthday.equals(v)) {
            childDate.setTime(arr.get(position).getBirthday());
            year = childDate.getYear()+1900;
            month = childDate.getMonth();
            day = childDate.getDate();
            showDateDialog();

        } else if (buttonImage.equals(v)) {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);

        } else if (buttonBack.equals(v)) {
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();


        } else if (buttonDelete.equals(v)) {
            childInfo.deleteChildImage(position, getContext());

            if (arr.get(position).getActive()){
                arr.remove(position);
                if (arr.size() > 0){
                    arr.get(0).setActive(true);
                }
            } else
                arr.remove(position);

            childInfo.setChildArr(arr, getContext());
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
                year,
                month,
                day
        );
        datePickerDialog.show();
    }

    /**
     * Method implemented from datePickerDialog
     * @param view
     * @param year
     * @param month
     * @param day
     */
    private void onDateSet(DatePicker view, int year, int month, int day) {
        Date date = new Date();
        //Minus because of Java API
        date.setYear(year-1900);
        date.setDate(day);
        date.setMonth(month);
        date.setMinutes(0);
        date.setHours(0);
        date.setSeconds(0);
        if (date.getTime() <= System.currentTimeMillis()) {
            buttonBirthday.setText(childInfo.monthText((month + 1)) + " " + day + " " + year);
            arr.get(position).setBirthday(date.getTime());
        }else {
            Toast.makeText(getContext(),"Not a valid date",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Getter for the birthday
     * @param index For the child array
     * @return
     */
    private String getBirthday(int index) {
        Calendar calendar = Calendar.getInstance();
        String date1, date2, date3;
            calendar.setTimeInMillis(arr.get(index).getBirthday());
            date1 = calendar.getTime().toString().substring(4, 10);
            date2 = calendar.getTime().toString().substring(30, 34);
            date3 = date1 + " " + date2;
            return date3;
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
                imageUri = data.getData();
                editBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                bitmapTemp = editBitmap;
                editBitmap = Bitmap.createBitmap(bitmapTemp, 0,0, editBitmap.getWidth(),editBitmap.getHeight(),matrix,true);
                editBitmap = Bitmap.createScaledBitmap(editBitmap,200,200,true);

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(imageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
                cursor.close();

                degree = getCameraPhotoOrientation(getContext(), imageUri, filePath);
                matrix.setRotate(degree);

                //Get round image
                Glide.with(this).load(editBitmap).
                        apply(RequestOptions.circleCropTransform())
                        .into(image);

                if (image != null){
                    image.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

}
