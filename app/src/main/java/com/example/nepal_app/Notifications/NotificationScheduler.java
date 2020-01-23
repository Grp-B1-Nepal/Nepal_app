package com.example.nepal_app.Notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.MainActivity;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Author Gustav Emil Nobert s185031
 */

public class NotificationScheduler {

    public static final int DAILY_REMINDER_REQUEST_CODE=100;
    public static final String TAG="NotificationScheduler";
    private static String CHANNEL_ID = "the100goldendaysNepalB1";

    /**
     * This method sets the reminder to make sure the alarm triggers when it's supposed to.
     * @param context
     * @param cls
     */
    public static void setReminder(Context context, Class<?> cls) {

        Log.d(TAG, "setReminder: start");
        System.out.println("setReminder: start");

        // cancel already scheduled reminders - not sure if i need this or not.
        cancelReminder(context,cls);

        //This enables the reciever across restart.     https://developer.android.com/training/scheduling/alarms.html
        ComponentName receiver = new ComponentName(context, MyReciever.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        //This is the intent to be used. The alarm manager is the one that triggers the broadcast reciever.
        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Date date = new Date();
        date.setTime(0);
        date.setDate(30);
        // Gets the current time because that is the current date.
        //Calendar.getInstance().getTimeInMillis() + daysinmillisec
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + date.getTime(), Calendar.getInstance().getTimeInMillis() + date.getTime(), pendingIntent);

        Log.d(TAG, "setReminder: end");
        System.out.println("setReminder: end");
    }

    /**
     * Cancels a reminder to avoid having multiple.
     * @param context
     * @param cls
     */
    public static void cancelReminder(Context context,Class<?> cls) {
        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    /**
     * Method taken  from androids offical documentation. A few changes made
     * @param context
     * @return
     */
    public static void createNotification(Context context) {
        // Create an explicit intent for an Activity in your app

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        ArrayList<String> stringlist = new ArrayList<>();
        //Returns the correct information from strings based on an int.
        Collections.addAll(stringlist,context.getResources().getStringArray(R.array.Push_descriptions));

        //Needs some form of baseline such that it doesn't throw an array out of bounds longer down if there are no children.
        String descriptionstring = stringlist.get(0);
        //This finds the correct information from the string ressource array.
        if (ChildInfo.getInstance().getChildArr(context).size() != 0) {
            descriptionstring = stringlist.get(ChildInfo.getInstance().getMonthProgress());
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.mom_icon)
                .setSound(alarmSound)
                .setContentTitle("New information in the app!")
                .setContentText(descriptionstring)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                // Set the intent that will fire when the user taps the notification
                // Right now we only have one activity which means it will trigger the main activity.
                .setStyle(new NotificationCompat.BigTextStyle());

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, builder.build());
    }
}
