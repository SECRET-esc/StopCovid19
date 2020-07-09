package com.example.covid19app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.os.Build;
import android.os.IBinder;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

import static java.lang.Thread.sleep;

public class basicallyService extends Service
{
    private NotificationManagerCompat notificationsManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        //startsendmessage("Hello, how are you?");

    }

    private void showNotification(String message){

        Intent notificationcontent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationcontent,
                0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel1")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Тестовое сообщение!")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "PACKAGE_NAME",
                    "APP_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        notificationManager.notify(randomInt,builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String a_switch = intent.getStringExtra("Switch");

        Intent notificationcontent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationcontent,
                0);
        Notification notification = new NotificationCompat.Builder(this,"MyChannelId")
                .setContentTitle("Inspect COVID-19!")
                .setContentText(a_switch)
                .setSmallIcon(R.drawable.virus)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(100,notification);


        return START_NOT_STICKY;

    }

    public  void startsendmessage(String message){
        while (true)
        {
            showNotification(message);
            try {
                sleep(10000);
            } catch (Exception e){}


        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}