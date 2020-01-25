package com.example.nepal_app;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.Notifications.MyReciever;
import com.example.nepal_app.Notifications.NotificationScheduler;
import com.example.nepal_app.UI.Fragments.Navigation.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "the100goldendaysNepalB1";
    //får måske brug for det her  https://stackoverflow.com/questions/3009059/android-pending-intent-notification-problem  int requestID = (int) System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create notification channel skal være og det gør ikke noget at den bliver kaldt hver gang.
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        createNotificationChannel();
        NotificationScheduler.setReminder(this, MyReciever.class);
        if (savedInstanceState==null) {
            openFragment(new HomeFragment());
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
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
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

//ellers lav aktiviterer mellem de andres? ved godt vi ikke har aftalt hvordan vi gør det
