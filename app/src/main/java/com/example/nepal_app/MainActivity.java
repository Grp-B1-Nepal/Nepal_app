package com.example.nepal_app;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.MyReciever.MyReciever;
import com.example.nepal_app.UI.Fragments.Navigation.HomeFragment;
import com.example.nepal_app.MyReciever.LocalData;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String TAG = "RemindMe";
    LocalData localData;
    int hour, min;
    ClipboardManager myClipboard;
    private String CHANNEL_ID = "channelID";
    int notificationId = 1;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create notification channel skal være og det gør ikke noget at den bliver kaldt hver gang.
        createNotificationChannel();
//Creates the calendar for planning the notification.
        //Calendar calendar = Calendar.getInstance();

        //calendar.set(Calendar.MONTH, 1);
        //calendar.set(Calendar.YEAR, 2020);
        //calendar.set(Calendar.DAY_OF_MONTH, 14);

        //calendar.set(Calendar.HOUR_OF_DAY, 11);
        //calendar.set(Calendar.MINUTE, 15);
        //calendar.set(Calendar.SECOND, 0);
        //calendar.set(Calendar.AM_PM,Calendar.AM);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification that you must define
        NotificationCompat.Builder builder = createNotification();
// notify viser beskeden.
        notificationManager.notify(notificationId, builder.build());
//I think the intent is the notification itself
        Intent myIntent = new Intent(MainActivity.this, MyReciever.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
//Alarm manageren er den der sender beskeden ud.
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        setContentView(R.layout.activity_main);
        openFragment(new HomeFragment());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


    /**
     * Creates the channel. Taken from android documentation.
     * https://developer.android.com/training/notify-user/build-notification
     */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private NotificationCompat.Builder createNotification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.person_add_icon)
                .setContentTitle("Anders er til mænd")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder;
    }
}

//ellers lav aktiviterer mellem de andres? ved godt vi ikke har aftalt hvordan vi gør det
