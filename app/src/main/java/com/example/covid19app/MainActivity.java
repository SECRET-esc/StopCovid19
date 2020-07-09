package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private Switch a_switch;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a_switch = findViewById(R.id.illswitch);
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }
    public void startService(View v) throws InterruptedException {


        boolean switchState = a_switch.isChecked();

        if(switchState)
        {
            message = "You ill COVID-19! Please stay home!";

        }
        else
        {
            message = "You don`t ill COVID-19! You can go to work!";
        }

        Intent serviceIntent = new Intent(this, basicallyService.class);
        serviceIntent.putExtra("Switch", message);
        startService(serviceIntent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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



    public void stopService(View v)
    {
        Intent serviceIntent = new Intent(this, basicallyService.class);
        stopService(serviceIntent);
    }
    public native String stringFromJNI();
}
