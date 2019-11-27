package com.example.nepal_app.Factory;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.nepal_app.Fragments.child.ChildObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class POJO {
    private static final POJO ourInstance = new POJO();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    public static POJO getInstance() {
        return ourInstance;
    }
    private ArrayList<Bitmap> bitmap = new ArrayList<>();
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

    public ArrayList<Bitmap> getBitmap(Context context){
        for (int i = 0; i <childArr.size() ; i++) {
            loadImage(context,childArr.get(i).getName());
        }
        return bitmap;
    }

    //TODO open choose piture window in the call

    public void setBitmap(ArrayList<Bitmap> bitmaps){
        bitmap.clear();

        bitmap.addAll(bitmaps);
    }

    public void deleteChild(int position, Context context){
        deleteImage(childArr.get(position).getName(), context);
        childArr.remove(position);


    }

    public void setSingleImage(Bitmap image, int postion){
        bitmap.set(postion,image);
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

    private void loadImage(Context context, String name){

        SharedPreferences settings = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        String loadedImage = settings.getString(name, null);

        if (loadedImage != null){
            byte[] b = Base64.decode(loadedImage, Base64.DEFAULT);
            Bitmap s = BitmapFactory.decodeByteArray(b,0,b.length);
            bitmap.add(s);

        }
        if (bitmap.size() == 0){
            bitmap = new ArrayList<>();
        }
    }
}
