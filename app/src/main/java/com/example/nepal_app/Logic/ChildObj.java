package com.example.nepal_app.Logic;



public class ChildObj{

    private String name;
    private long birthday;
    private String gender;


    /**
     *Constructor to Child object.
     * @param name Name of the Child
     * @param birthday Birthday of the child
     * @param gender The gender of the child
     */
    public ChildObj(String name, long birthday, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;

    }

    /**
     * Getter for the name of the name of the child
     * @return the name
     */
    public String getName(){ return name; }

    /**
     * Getter for the gender of the child
     * @return the gender
     */
    public String getGender(){
        return gender;
    }

    /**
     * Getter for the birthday of the child
     * @return the birthday
     */
    public long getBirthday(){
        return birthday;
    }

    /**
     * Setter for the name of the child
     * @param name parameter for the new name
     */
    public void setName(String name){this.name = name;}

    /**
     * Setter for the gender of the child
     * @param gender parameter for the new gender
     */
    public void setGender(String gender){this.gender = gender;}

    /**
     * Setter for the birthday for the child
     * @param birthday paramter for the new birthday
     */
    public void setBirthday(long birthday){this.birthday = birthday;}

}
