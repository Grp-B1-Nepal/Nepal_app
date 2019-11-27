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

public class POJO {
    private static final POJO ourInstance = new POJO();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    public static POJO getInstance() {
        return ourInstance;
    }
    private Bitmap bitmap;
    private int position;

    private POJO() {
    }

    public void setChildArr(ArrayList<ChildObj> arr) {
        childArr.clear();
        childArr.addAll(arr);
    }


    public ArrayList<ChildObj> getChildArr(Context context) {
        childArr.clear();
        loadChild(context);
        return childArr;
    }

    public Bitmap getBitmap(Context context, String name){

        return loadImage(context,name);
    }

    //TODO open choose piture window in the call

    public void setBitmap(Bitmap bitmaps,String name, Context context){
        this.bitmap = bitmaps;
        saveImage(context,name);

    }

    public void deleteChild(int position, Context context){
        deleteImage(childArr.get(position).getName(), context);




    }

    private void deleteImage(String name, Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(name).apply();


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

    public int getPosition(){return position;}

    public void setPosition(int position) {this.position = position;}

    private Bitmap loadImage(Context context, String name){

        SharedPreferences settings = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        String loadedImage = settings.getString(name, null);

        if (loadedImage != null){
            byte[] b = Base64.decode(loadedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        }
        return bitmap;
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
}
