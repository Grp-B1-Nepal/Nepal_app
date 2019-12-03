package com.example.nepal_app.Factory;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.nepal_app.Fragments.child.ChildObj;

import java.util.ArrayList;

public interface IPOJO {

    void setChildArr(ArrayList<ChildObj> arr, Context context);

    ArrayList<ChildObj> getChildArr(Context context);

    Bitmap getBitmap(Context context, String name);

    void setBitmap(Bitmap bitmaps,String name, Context context);

    void deleteChild(int position, Context context);

    void newNameImage(Context context, String oldName, String newName);

    int getPosition();

    void setPosition(int position);

    String monthText(int intMonth);





}
