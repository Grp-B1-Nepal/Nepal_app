package com.example.nepal_app.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.nepal_app.Fragments.ProfileFragment;
import com.example.nepal_app.Fragments.child.EditChild;
import com.example.nepal_app.R;
import com.example.nepal_app.Fragments.child.ChildObj;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Inspiration taken from https://www.youtube.com/watch?v=q2XA0Pe2W04
 */

public class Adaptor_Listview extends ArrayAdapter<String>  {

    private Context context;
    private ArrayList<ChildObj> childArr;
    private ArrayList<Bitmap> image;
    private String[] birthday;


    public Adaptor_Listview(Context context, ArrayList<ChildObj> arr, ArrayList<Bitmap> image, String[] birthday){
        super(context, R.layout.profil_liste_element);
        this.childArr = arr;
        this.context = context;
        this.image = image;
        this.birthday = birthday;
    }

    @Override
    public int getCount() {
        return childArr.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.profil_liste_element, parent, false);
            viewHolder.childrenImage = convertView.findViewById(R.id.image_children);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.birthday = convertView.findViewById(R.id.birthday);
            viewHolder.gender = convertView.findViewById(R.id.gender);
            viewHolder.edit = convertView.findViewById(R.id.edit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.childrenImage.setImageBitmap(image.get(position));
            viewHolder.name.setText(childArr.get(position).getName());
            viewHolder.birthday.setText(birthday[position]);
            viewHolder.gender.setText(childArr.get(position).getGender());
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), EditChild.class);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                }
            });


        return convertView;
    }


    static class ViewHolder{
        ImageView childrenImage;
        TextView name;
        TextView birthday;
        TextView gender;
        Button edit;
    }


}