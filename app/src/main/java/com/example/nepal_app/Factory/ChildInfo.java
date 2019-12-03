package com.example.nepal_app.Factory;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.nepal_app.Fragments.child.ChildObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChildInfo implements IChildInfo {
    private static final ChildInfo ourInstance = new ChildInfo();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    public static ChildInfo getInstance() {
        return ourInstance;
    }
    private Bitmap bitmap;
    private int position;

    private ChildInfo() {
    }

    @Override
    public void setChildArr(ArrayList<ChildObj> arr, Context context) {

        saveChild(context, arr);
    }

    @Override
    public ArrayList<ChildObj> getChildArr(Context context) {
        childArr.clear();
        loadChild(context);
        return childArr;
    }
    @Override
    public Bitmap getBitmap(Context context, String name){

        return loadImage(context,name);
    }

    @Override
    public void setBitmap(Bitmap bitmaps,String name, Context context){
        this.bitmap = bitmaps;
        saveImage(context,name);

    }

    @Override
    public void deleteChild(int position, Context context){
        deleteImage(childArr.get(position).getName(), context);

    }

    @Override
    public void newNameImage(Context context, String oldName, String newName){
     saveImageNewName(context,oldName,newName);
    }

    @Override
    public int getPosition(){return position;}

    @Override
    public void setPosition(int position) {this.position = position;}


    @Override
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

    private void saveChild(Context context, ArrayList<ChildObj> arr){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Children", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("ChildArr",json);
        editor.apply();
    }

    private void deleteImage(String name, Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(name).apply();
    }

    private void saveImageNewName(Context context, String oldName, String newName){

        bitmap = loadImage(context, oldName);

        deleteImage(oldName, context);

        saveImage(context,newName);
    }

    private void saveImage(Context context, String name){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap = Bitmap.createScaledBitmap(bitmap,60,60,true);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArr = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteArr,Base64.DEFAULT);


        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, encodeImage);
        editor.apply();
    }

    private Bitmap loadImage(Context context, String name){

        SharedPreferences settings = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        String loadedImage = settings.getString(name, null);

        if (loadedImage != null){
            byte[] b = Base64.decode(loadedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        }
        return bitmap;
    }


    private void loadChild(Context context) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences("Children", Context.MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ChildArr", null);
        Type type = new TypeToken<ArrayList<ChildObj>>() {
        }.getType();
        childArr = gson.fromJson(json, type);
        if (childArr == null){
            childArr = new ArrayList<>();
        }
    }

}
