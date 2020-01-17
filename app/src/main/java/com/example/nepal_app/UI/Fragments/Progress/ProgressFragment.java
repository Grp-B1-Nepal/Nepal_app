package com.example.nepal_app.UI.Fragments.Progress;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nepal_app.Logic.Objects.ChildObj;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.R;

import java.util.ArrayList;


public class ProgressFragment extends Fragment implements View.OnClickListener {
    ImageView image;
    TextView info, age, height, weight, head, info2;
    private Button b1, b2, b3, b4, b5, b6, b7, b8,b9,b10,b11,b12;
    private int monthAge;
    private View rod;
    private Bitmap imageChild;
    private ChildInfo childInfo;
    private ArrayList<ChildObj> childArr = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rod = inflater.inflate(R.layout.fragment_progress, container, false);

        info = rod.findViewById(R.id.fragmentProgress_InfoText);
        info2 = rod.findViewById(R.id.fragmentProgress_InfoText2);
        age = rod.findViewById(R.id.fragmentProgress_Headline);
        head = rod.findViewById(R.id.fragmentProgress_Headline2);
        height = rod.findViewById(R.id.progress_height);
        weight = rod.findViewById(R.id.progress_weight);
        image = rod.findViewById(R.id.fragmentProgress_imageView);

        b1 = rod.findViewById(R.id.b1);
        b2 = rod.findViewById(R.id.b2);
        b3 = rod.findViewById(R.id.b3);
        b4 = rod.findViewById(R.id.b4);
        b5 = rod.findViewById(R.id.b5);
        b6 = rod.findViewById(R.id.b6);
        b7 = rod.findViewById(R.id.b7);
        b8 = rod.findViewById(R.id.b8);
        b9 = rod.findViewById(R.id.b9);
        b10 = rod.findViewById(R.id.b10);
        b11 = rod.findViewById(R.id.b11);
        b12 = rod.findViewById(R.id.b12);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);

        b1.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
        updateInfo(1);

        childInfo = ChildInfo.getInstance();

        childArr = childInfo.getChildArr(getContext());


        if (childArr.size() != 0){
            imageChild = childInfo.getBitmap(getContext(),childArr.get(childInfo.getActiveChild()).getName());
            monthAge = childInfo.getMonthProgress();
            updateInfo(monthAge);
            image.setImageBitmap(imageChild);

            Glide.with(this).load(imageChild).
                    apply(RequestOptions.circleCropTransform())
                    .into(image);
        }

        // Inflate the layout for this fragment
        return rod;
    }
    public void onClick(View v) {
        //Paints the background and changes the informationnum range number. The number is passed on to the next fragment such that it knows what information it should get.
        if (v == b1) {
            updateInfo(1);
        } else if (v == b2) {
            updateInfo(2);
        } else if (v == b3) {
            updateInfo(3);
        }else if (v == b4) {
            updateInfo(4);
        }else if (v == b5) {
            updateInfo(5);
        }else if (v == b6) {
            updateInfo(6);
        }else if (v == b7) {
            updateInfo(7);
        }else if (v == b8) {
            updateInfo(8);
        }else if (v == b9) {
            updateInfo(9);
        }else if (v == b10) {
            updateInfo(10);
        }else if (v == b11) {
            updateInfo(11);
        } else if (v == b12) {
            updateInfo(12);
        }
    }

    public void resetColors() {
        b1.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b2.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b3.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b4.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b5.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b6.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b7.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b8.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b9.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b10.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b11.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
        b12.setBackground(getResources().getDrawable(R.drawable.scroll_buttons));
    }
    public void updateInfo(int agerange){
        if (agerange <= 1){
            resetColors();
            b1.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight1);
            height.setText(R.string.height1);
            age.setText(R.string.age1);
            head.setText(R.string.head1);
            info.setText(R.string.info1_1);
            info2.setText(R.string.info2_1);
        }
        if (agerange == 2){
            resetColors();
            b2.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight2);
            height.setText(R.string.height2);
            age.setText(R.string.age2);
            head.setText(R.string.head2);
            info.setText(R.string.info1_2);
            info2.setText(R.string.info2_2);
        }
        if (agerange == 3){
            resetColors();
            b3.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight3);
            height.setText(R.string.height3);
            age.setText(R.string.age3);
            head.setText(R.string.head3);
            info.setText(R.string.info1_3);
            info2.setText(R.string.info2_3);
        }
        if (agerange == 4){
            resetColors();
            b4.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight4);
            height.setText(R.string.height4);
            age.setText(R.string.age4);
            head.setText(R.string.head4);
            info.setText(R.string.info1_4);
            info2.setText(R.string.info2_4);
        }
        if (agerange == 5){
            resetColors();
            b5.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight5);
            height.setText(R.string.height5);
            age.setText(R.string.age5);
            head.setText(R.string.head5);
            info.setText(R.string.info1_5);
            info2.setText(R.string.info2_5);
        }
        if (agerange == 6){
            resetColors();
            b6.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight6);
            height.setText(R.string.height6);
            age.setText(R.string.age6);
            head.setText(R.string.head6);
            info.setText(R.string.info1_6);
            info2.setText(R.string.info2_6);
        }
        if (agerange == 7){
            resetColors();
            b7.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight7);
            height.setText(R.string.height7);
            age.setText(R.string.age7);
            head.setText(R.string.head7);
            info.setText(R.string.info1_7);
            info2.setText(R.string.info2_7
            );
        }
        if (agerange == 8){
            resetColors();
            b8.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight8);
            height.setText(R.string.height8);
            age.setText(R.string.age8);
            head.setText(R.string.head8);
            info.setText(R.string.info1_8);
            info2.setText(R.string.info2_8);
        }
        if (agerange == 9){
            resetColors();
            b9.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight9);
            height.setText(R.string.height9);
            age.setText(R.string.age9);
            head.setText(R.string.head9);
            info.setText(R.string.info1_9);
            info2.setText(R.string.info2_9);
        }
        if (agerange == 10){
            resetColors();
            b10.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight10);
            height.setText(R.string.height10);
            age.setText(R.string.age10);
            head.setText(R.string.head10);
            info.setText(R.string.info1_10);
            info2.setText(R.string.info2_10);
        }
        if (agerange == 11){
            resetColors();
            b11.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight11);
            height.setText(R.string.height11);
            age.setText(R.string.age11);
            head.setText(R.string.head11);
            info.setText(R.string.info1_11);
            info2.setText(R.string.info2_11);
        }
        if (agerange >= 12){
            resetColors();
            b12.setBackground(getResources().getDrawable(R.drawable.scroll_buttons_pressed));
            weight.setText(R.string.weight12);
            height.setText(R.string.height12);
            age.setText(R.string.age12);
            head.setText(R.string.head12);
            info.setText(R.string.info1_12);
            info2.setText(R.string.info2_12);
        }
    }
}