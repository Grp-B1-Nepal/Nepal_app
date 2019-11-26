package com.example.nepal_app.Factory;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.nepal_app.fragments.child.ChildObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;

public class POJO {
    private static final POJO ourInstance = new POJO();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    public static POJO getInstance() {
        return ourInstance;
    }
    private ArrayList<String> image = new ArrayList<>();


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

    public ArrayList<String> getURI(Context context){
        loadImage(context);
        return image;
    }

    //TODO open choose piture window in the call
    public void setUri (Context context, URI image){
        changeImage(context,image);
    }

    public void setUri(ArrayList<Uri> uri){
        image.clear();

        for (int i = 0; i <uri.size() ; i++) {
            image.add(String.valueOf(uri.get(i)));
        }
    }

    public Uri getUri(){return Uri.parse(String.valueOf(image));}


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

    private void loadImage(Context context){
        SharedPreferences settings = context.getSharedPreferences("Image", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = settings.getString("Uri_image",null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        image = gson.fromJson(json,type);
        if (image == null){
            image= new ArrayList<>();
        }
    }

    private void changeImage(Context context, URI image){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uri_image", String.valueOf(image));
        editor.apply();


    }
}
