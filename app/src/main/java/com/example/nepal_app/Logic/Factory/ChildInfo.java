package com.example.nepal_app.Logic.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.nepal_app.Datalayer.CacheSaving;
import com.example.nepal_app.Logic.ChildObj;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChildInfo {
    private static final ChildInfo ourInstance = new ChildInfo();
    private ArrayList<ChildObj> childArr = new ArrayList<>();
    private CacheSaving cacheSaving = CacheSaving.getInstance();
    private Bitmap bitmap;
    private int position;

    private ChildInfo() {
    }

    //Creates instance for singleton
    public static ChildInfo getInstance() {
        return ourInstance;
    }

    /**
     *Setter for the childarr arraylist
     * @param arr is the array
     * @param context
     */
    public void setChildArr(ArrayList<ChildObj> arr, Context context) {

        cacheSaving.saveChild(context, arr);
    }

    /**
     * Getter for the Childarr arraylist
     * @param context
     * @return the arraylist
     */
    public ArrayList<ChildObj> getChildArr(Context context) {
        childArr.clear();
        childArr = cacheSaving.loadChild(context);
        return childArr;
    }

    /**
     * Getter for the Bitmap
     * @param context
     * @param name name of the child
     * @return The Bitmap for the given Child
     */
    public Bitmap getBitmap(Context context, String name){

        return cacheSaving.loadImage(context,name);
    }

    /**
     * Setter for the Bitmap
     * @param bitmaps
     * @param name Name of the child
     * @param context
     */
    public void setBitmap(Bitmap bitmaps,String name, Context context){
        this.bitmap = bitmaps;
        cacheSaving.saveImage(context,name, bitmap);

    }

    /**'
     * Getter to get the active child
     * @return the position in the array the child is located.
     */
    public int getActiveChild(){
        int position = -1;
        for (int i = 0; i <childArr.size() ; i++) {
            if (childArr.get(i).getActive()){
                position = i;
            }
        }
        return position;
    }

    /**
     * Deletes the child
     * @param position Postion where the child is saved
     * @param context
     */
    public void deleteChildImage(int position, Context context){
        cacheSaving.deleteImage(childArr.get(position).getName(), context);

    }

    /**
     * Switching picture from old name to new name
     * @param context
     * @param oldName Current name of the child
     * @param newName New name of the child
     */
    public void newNameImage(Context context, String oldName, String newName){
     cacheSaving.saveImageNewName(context,oldName,newName);
    }

    /**
     * Getter for the position to the ChildArr arraylist
     * @return the position
     */
    public int getPosition(){return position;}

    /**
     * Setter for position
     * @param position
     */
    public void setPosition(int position) {this.position = position;}


    /**
     * Calculate how many months old the child is
     */
    public int monthProgress(){
        long progress;
        int monthAge;
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        long a = date.getTime();
        Date p = new Date();
        long b = childArr.get(getActiveChild()).getBirthday();
        p.setTime(b);
        progress = (a - b);
        progress = progress/(1000*60*60*24);
        progress = (int) Math.ceil(progress/30);
        monthAge = (int) progress;
        return monthAge;
    }

    /**
     * Calculate the age of the child
     */
    public long[] progressAge(){
        Date d = new Date();
        long[] progress = new long[childArr.size()];
        for (int i = 0; i <childArr.size() ; i++) {
            d.getDate();
            d.setHours(0);
            d.setMinutes(0);
            long b = d.getTime();
            long q =childArr.get(i).getBirthday();
            Date p = new Date();
            p.setTime(q);
            progress[i] =  (b - q);
            long a = (long) Math.floor(progress[i]/(1000*60*60*24));
            progress[i] = a;
        }
        return progress;
    }

    /**
     * Sets the birthday to the correct format to in the string[] birthday
     */
    public String[] getBirthdayString() {
        Calendar calendar = Calendar.getInstance();
        String[] birthday = new String[childArr.size()];
        String date1, date2, date3;
        for (int i = 0; i < childArr.size(); i++) {
            calendar.setTimeInMillis(childArr.get(i).getBirthday());
            date1 = calendar.getTime().toString().substring(4, 10);
            date2 = calendar.getTime().toString().substring(30, 34);
            date3 = date1 + " " + date2;
            birthday[i] = date3;
        }
        return birthday;
    }

    /**
     * Switch that returns the string of a month
     * @param intMonth
     * @return
     */
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
