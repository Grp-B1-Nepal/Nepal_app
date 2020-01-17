package com.example.nepal_app.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MyReciever extends BroadcastReceiver {
    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onReceive: start" + Calendar.getInstance().get(Calendar.MINUTE) + "minutes " + Calendar.getInstance().get(Calendar.DATE));
        //Theese if statements makes sure that it starts up agian if the telefon reboots.
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler.setReminder(context, MyReciever.class);
                return;
            }
        }
        //Trigger the notification
        NotificationScheduler.createNotification(context);
        Log.d(TAG, "onReceive: end");
    }
}
