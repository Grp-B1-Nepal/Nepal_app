package com.example.nepal_app.Fragments.child;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;


public class EditChild extends Fragment implements View.OnClickListener {
    int position;
    TextView textName, textGender, textBirthday, textImage;
    Button birthday, newImage, button_back;
    EditText editName, editGender;
    String   newBirthday;
    Bitmap   editBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = new Bundle();
        position = bundle.getInt("postion");


        textName = container.findViewById(R.id.textView_editName);
        textGender = container.findViewById(R.id.textView_editGender);
        textBirthday = container.findViewById(R.id.textView_editBirthday);
        textImage = container.findViewById(R.id.textView_editImage);


        editName = container.findViewById(R.id.editText_editName);
        editGender = container.findViewById(R.id.editText_editGender);

        birthday = container.findViewById(R.id.button_newBirthday);
        newImage = container.findViewById(R.id.button_editImage);

        button_back = container.findViewById(R.id.button_editBack);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_child, container, false);

    }


    @Override
    public void onClick(View v) {

        if (v == editName){

        }

        if (v == editGender){

        }
        if (v == birthday){

        }
        if (v == newImage){

        }
        if (v == button_back){

        }


    }
}
