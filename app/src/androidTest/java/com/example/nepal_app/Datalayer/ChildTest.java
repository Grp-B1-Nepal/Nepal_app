package com.example.nepal_app.Datalayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.InstrumentationRegistry;

import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.Logic.Objects.ChildObj;
import com.example.nepal_app.R;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ChildTest  {
    ChildInfo childInfo = ChildInfo.getInstance();
    CacheSaving cacheSaving = new CacheSaving();
    ArrayList<ChildObj> childArr = new ArrayList<>();
    Context context = InstrumentationRegistry.getTargetContext();
    Bitmap bitmap;

    @Test
    public void childCreationAndDeletion(){
        Date date = new Date();
        date.setYear(2019-1900);
        date.setMonth(4);
        date.setDate(20);
        long birthday = date.getTime();
        childArr.add(new ChildObj("Monkey",birthday,"boy",true));

        cacheSaving.saveChild(context,childArr);
        childArr.clear();
        childArr = cacheSaving.loadChild(context);

        assertEquals(childArr.size(),1);
        assertEquals(childArr.get(0).getName(),"Monkey");
        assertEquals(childArr.get(0).getBirthday(),birthday);
        assertEquals(childArr.get(0).getGender(),"boy");
        assertTrue(childArr.get(0).getActive());


        childArr.clear();
        cacheSaving.saveChild(context,childArr);
        childArr = cacheSaving.loadChild(context);
        assertEquals(childArr.size(),0);


        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.activity_playtime);
        String name = "Monkey";
        cacheSaving.saveImage(context,name,bitmap);
        //Can't check if it's been saved because of the change of size because of memory use
        //works with debugger

        cacheSaving.deleteImage(name,context);
        bitmap = cacheSaving.loadImage(context,name);
        assertNull(bitmap);


        //new name
        String newName = "Cake";
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.activity_playtime);
        cacheSaving.saveImage(context,name,bitmap);
        bitmap = cacheSaving.loadImage(context,name);
        childInfo.newNameImage(context,name,newName);
        Bitmap imageNewName = cacheSaving.loadImage(context,newName);

        //Compress the bitmap to a byte[] to compare them with assertArrayEquals
        //Because assertEquals fails because of the id
        ByteArrayOutputStream byteArrayOutputStreamBitmap = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStreamBitmap);
        byte[] byteArrBitmap = byteArrayOutputStreamBitmap.toByteArray();

        ByteArrayOutputStream byteArrayOutputStreamLoad = new ByteArrayOutputStream();
        imageNewName.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStreamLoad);
        byte[] byteArrLoad = byteArrayOutputStreamLoad.toByteArray();

        assertArrayEquals(byteArrBitmap,byteArrLoad);

        cacheSaving.deleteImage(newName,context);
    }
}