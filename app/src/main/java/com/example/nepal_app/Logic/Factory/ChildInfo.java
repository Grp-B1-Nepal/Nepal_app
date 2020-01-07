package com.example.nepal_app.Logic.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.nepal_app.Datalayer.CacheSaving;
import com.example.nepal_app.Logic.ChildObj;
import java.util.ArrayList;

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
     cacheSaving.saveImageNewName(context,oldName,newName, bitmap);
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
