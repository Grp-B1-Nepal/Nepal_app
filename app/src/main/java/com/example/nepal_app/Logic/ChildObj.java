package com.example.nepal_app.Logic;

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

    public void setName(String name){this.name = name;}
    public void setGender(String gender){this.gender = gender;}
    public void setBirthday(long birthday){this.birthday = birthday;}

}
