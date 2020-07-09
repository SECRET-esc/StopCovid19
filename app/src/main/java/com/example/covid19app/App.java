package com.example.covid19app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Random;

public class App extends Application
{
    public static final String NOTIFICATION_CHANNEL_ID = "channel1";
    public static final String CHANNEL_ID = "MyChannelId";

    @Override
    public void onCreate()
    {
        super.onCreate();

        createNotificationsChannel();
    }
    private void createNotificationsChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel ServiceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(ServiceChannel);

        }

    }

}
