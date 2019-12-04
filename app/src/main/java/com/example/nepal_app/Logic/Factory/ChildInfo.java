package com.example.nepal_app.Logic.Factory;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.nepal_app.Datalayer.CacheSaving;
import com.example.nepal_app.Logic.ChildObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChildInfo {
    private static final ChildInfo ourInstance = new ChildInfo();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    private CacheSaving cacheSaving = CacheSaving.getInstance();
    private Bitmap bitmap;
    private int position;

    private ChildInfo() {
    }


    public static ChildInfo getInstance() {
        return ourInstance;
    }


    public void setChildArr(ArrayList<ChildObj> arr, Context context) {

        cacheSaving.saveChild(context, arr);
    }

    public ArrayList<ChildObj> getChildArr(Context context) {
        childArr.clear();
        childArr = cacheSaving.loadChild(context);
        return childArr;
    }

    public Bitmap getBitmap(Context context, String name){

        return cacheSaving.loadImage(context,name);
    }

    public void setBitmap(Bitmap bitmaps,String name, Context context){
        this.bitmap = bitmaps;
        cacheSaving.saveImage(context,name, bitmap);

    }

    public void deleteChild(int position, Context context){
        cacheSaving.deleteImage(childArr.get(position).getName(), context);

    }

    public void newNameImage(Context context, String oldName, String newName){
     cacheSaving.saveImageNewName(context,oldName,newName, bitmap);
    }

    public int getPosition(){return position;}
    public void setPosition(int position) {this.position = position;}

    public String monthText(int intMonth){
        String month = null;

        switch (intMonth){
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;

        }

        return month;
    }
}
