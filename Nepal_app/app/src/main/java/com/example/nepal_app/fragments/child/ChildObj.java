package com.example.nepal_app.fragments.child;

import android.net.Uri;


public class ChildObj{

    private String name;
    private long birthday;
    private String gender;

    //Constructor to Child objektet.
    public ChildObj(String name, long birthday, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;

    }


    public String getName(){ return name; }
    public String getGender(){
        return gender;
    }
    public long getBirthday(){
        return birthday;
    }

}
