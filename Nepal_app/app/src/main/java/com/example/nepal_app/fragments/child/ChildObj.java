package com.example.nepal_app.fragments.child;

public class ChildObj{

    private String name;
    long birthday;
    String gender;

    //Constructor til Child objektet.
    public ChildObj(String name, long birthday, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public long getBirthday(){
        return birthday;
    }
}