package com.example.nepal_app.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MyReciever extends BroadcastReceiver {
    String TAG = "AlarmReceiver";

    /**
     * On recieve is the automatic method called when it's triggered by the alarm manager in the sceduleNotification in the notification scheduler method.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: start" + Calendar.getInstance().get(Calendar.MINUTE) + " minutes " + Calendar.getInstance().getTimeInMillis()%10);
        //Theese if statements makes sure that it starts up agian if the telefon reboots.
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler.createNotification(context);
                return;
            }
        }
        //Triggers the notification
        NotificationScheduler.createNotification(context);
        Log.d(TAG, "onReceive: end");
    }
}
