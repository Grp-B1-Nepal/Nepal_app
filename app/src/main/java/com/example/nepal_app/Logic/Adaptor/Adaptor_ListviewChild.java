
package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
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
import androidx.fragment.app.FragmentActivity;

import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.UI.Fragments.Profile.Child.EditChild;
import com.example.nepal_app.R;
import com.example.nepal_app.Logic.ChildObj;

import java.util.ArrayList;

/**
 * Inspiration taken from https://www.youtube.com/watch?v=q2XA0Pe2W04
 */

public class Adaptor_ListviewChild extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<ChildObj> childArr;

    private String[] birthday;
    private ChildInfo childInfo;


    public Adaptor_ListviewChild(Context context, ArrayList<ChildObj> arr, String[] birthday) {
        super(context, R.layout.profil_liste_element);
        this.childArr = arr;
        this.context = context;
        this.birthday = birthday;
        childInfo = ChildInfo.getInstance();
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
        viewHolder.childrenImage.setImageBitmap(Bitmap.createScaledBitmap(childInfo.getBitmap(getContext(),childArr.get(position).getName()),120,120,false));
        viewHolder.name.setText(childArr.get(position).getName());
        viewHolder.birthday.setText(birthday[position]);
        viewHolder.gender.setText(childArr.get(position).getGender());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childInfo.setPosition(position);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new EditChild()).addToBackStack(null).commit();
            }
        });
        return convertView;
    }
    static class ViewHolder {
        ImageView childrenImage;
        TextView name;
        TextView birthday;
        TextView gender;
        Button edit;
    }
}

