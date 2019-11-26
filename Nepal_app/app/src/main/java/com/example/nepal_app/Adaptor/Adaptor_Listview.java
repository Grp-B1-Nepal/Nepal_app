package com.example.nepal_app.Adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nepal_app.R;
import com.example.nepal_app.Fragments.child.ChildObj;

import java.util.ArrayList;

/**
 * Inspiration taken from https://www.youtube.com/watch?v=q2XA0Pe2W04
 */

public class Adaptor_Listview extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<ChildObj> childArr;
    private ArrayList<Bitmap> image;

    public Adaptor_Listview(Context context, ArrayList<ChildObj> arr, ArrayList<Bitmap> image){
        super(context, R.layout.profil_liste_element);
        this.childArr = arr;
        this.context = context;
        this.image = image;
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
            viewHolder.childrenImage = (ImageView) convertView.findViewById(R.id.image_children);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.childrenImage.setImageBitmap(image.get(position));
            viewHolder.name.setText(childArr.get(position).getName());


        return convertView;
    }
    static class ViewHolder{
        ImageView childrenImage;
        TextView name;
    }


}