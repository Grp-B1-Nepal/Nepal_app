package com.example.nepal_app.fragments.child;

import android.net.Uri;

import android.net.Uri;

public class ChildObj{

    private String name;
    private long birthday;
    private String gender;
    private Uri image;

    //Constructor to Child objektet.
    public ChildObj(String name, long birthday, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    public ChildObj(String name, long birthday, String gender, Uri image) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.image = image;
    }


    public String getName(){ return name; }

    public String getGender(){
        return gender;
    }
    public long getBirthday(){
        return birthday;
    }
    public Uri getImage() {return image;}
}
