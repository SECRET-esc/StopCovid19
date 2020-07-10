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
        System.loadLibrary("sayhello");
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
        TextView text = findViewById(R.id.mytextview);
        text.setText(hello());
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
    public void stopService(View v)
    {
        Intent serviceIntent = new Intent(this, basicallyService.class);
        stopService(serviceIntent);
    }
    public native String stringFromJNI();
    public native  String hello();
}
