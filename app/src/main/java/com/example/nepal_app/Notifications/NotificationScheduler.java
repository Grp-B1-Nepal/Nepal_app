package com.example.nepal_app.Notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.nepal_app.MainActivity;
import com.example.nepal_app.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class NotificationScheduler {

    public static final int DAILY_REMINDER_REQUEST_CODE=100;
    public static final String TAG="NotificationScheduler";
    private static String CHANNEL_ID = "the100goldendaysNepalB1";
    static int notificationId = 1;

    public static void setReminder(Context context, Class<?> cls) {

        Log.d(TAG, "setReminder: start");
        System.out.println("setReminder: start");

        // cancel already scheduled reminders
        // cancelReminder(context,cls);

        //Componentname and package manager im not sure what does.
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        long daysinmillisec = 1000*60*60*24*14; //This is how many milliseconds a day is. Right now it triggers 30 days after download and 30 days after that.
        // Gets the current time because that is the current date.
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + daysinmillisec, Calendar.getInstance().getTimeInMillis() + daysinmillisec, pendingIntent);

        Log.d(TAG, "setReminder: end");
        System.out.println("setReminder: end");
    }

    /**
     * Not sure if i need this one or not, it was in an example i found.
     * - s185031 Gustav
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
     * Method taken  from androids offical documentation.
     *
     * @param context
     * @return
     */
    public static void createNotification(Context context) {
        // Create an explicit intent for an Activity in your app

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.person_add_icon)
                .setSound(alarmSound)
                .setContentTitle("Hej Nicklas!")
                .setContentText("Det er weekend i dag!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                // Right now we only have one activity which means it will trigger the main activity.
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, builder.build());
    }

    /**
     * Taken from an example off the net.
     * s185031
     * @param context
     * @param cls
     * @param title
     * @param content
     */

    public static void showNotification(Context context,Class<?> cls,String title,String content) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.person_add_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(alarmSound)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, builder.build());
    }
}
