
package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.UI.Fragments.Profile.Child.EditChild;
import com.example.nepal_app.R;
import com.example.nepal_app.Logic.Objects.ChildObj;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Inspiration taken from https://www.youtube.com/watch?v=q2XA0Pe2W04
 */

public class Adaptor_ListviewChild extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<ChildObj> childArr;

    private String[] birthday;
    private ChildInfo childInfo;
    private long[] progress;


    public Adaptor_ListviewChild(Context context, ArrayList<ChildObj> arr, String[] birthday, long[] progress) {
        super(context, R.layout.profil_liste_element);
        this.childArr = arr;
        this.context = context;
        this.birthday = birthday;
        this.progress = progress;
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
            viewHolder.progress = convertView.findViewById(R.id.num);
            viewHolder.progressBar = convertView.findViewById(R.id.progressBar2);
            viewHolder.active = convertView.findViewById(R.id.active);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(getContext()).load(childInfo.getBitmap(getContext(), childArr.get(position).getName())).
                apply(RequestOptions.circleCropTransform())
                .into(viewHolder.childrenImage);

        viewHolder.childrenImage.setImageBitmap(Bitmap.createScaledBitmap(childInfo.getBitmap(getContext(),childArr.get(position).getName()),120,120,false));
        viewHolder.name.setText(childArr.get(position).getName());
        viewHolder.birthday.setText(birthday[position]);
        viewHolder.gender.setText(childArr.get(position).getGender());
        viewHolder.progress.setText(progress[position] + " days old");
        viewHolder.progressBar.setProgress((int) progress[position]);

        if(childArr.size() > 1 ) {
            viewHolder.active.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < childArr.size(); i++) {
                        childArr.get(i).setActive(false);
                    }
                    childArr.get(position).setActive(true);

                    //TODO maybe rephrase
                    Toast.makeText(context, "You change to " + childArr.get(position).getName(), Toast.LENGTH_SHORT).show();
                    Collections.swap(childArr, position, 0);
                    childInfo.setChildArr(childArr, context);
                    //Updates the adaptor after the change
                    progress = childInfo.getProgressAge();
                    birthday = childInfo.getBirthdayString();
                    notifyDataSetChanged();
                }
            });
        }
        if (childArr.get(position).getActive()){
            viewHolder.active.setImageResource(R.drawable.full_star_fill);
        } else
            viewHolder.active.setImageResource(R.drawable.empty_star_fill);


        viewHolder.childrenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childInfo.setPosition(position);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new EditChild()).addToBackStack(null).commit();
            }
        });
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
        ImageView active;
        TextView name;
        TextView birthday;
        TextView gender;
        Button edit;
        TextView progress;
        ProgressBar progressBar;
    }

}

