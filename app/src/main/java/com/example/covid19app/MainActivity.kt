package com.example.covid19app

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orange.proximitynotification.ProximityNotificationService as service
import com.orange.proximitynotification.ble.BleSettings
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
            System.loadLibrary("sayhello")
        }
    }

     val bleSettings : BleSettings by lazy { BleSettings (
            serviceUuid = UUID.fromString("123e4567-e89b-12d3-a456-556642440000"),
            servicePayloadCharacteristicUuid = UUID.fromString("0"),
            backgroundServiceManufacturerDataIOS = byteArrayOf(), // Byte array of manufacturer data advertised by iOS in background. It depends on your service UUID
            txCompensationGain = 0, // Calibrated TxPowerLevel of your device
            rxCompensationGain = 0 // RSSI compensation gain of your device
    )
    }




    private var a_switch: Switch? = null
    private var message: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a_switch = findViewById(R.id.illswitch)
        val tv = findViewById<TextView>(R.id.sample_text)
        tv.text = stringFromJNI()
        val text = findViewById<TextView>(R.id.mytextview)
        text.text = hello()
    }


    @Throws(InterruptedException::class)
    fun startService(v: View?) {
        val switchState = a_switch!!.isChecked
        message = if (switchState) {
            "You ill COVID-19! Please stay home!"
        } else {
            "You don`t ill COVID-19! You can go to work!"
        }
        val serviceIntent = Intent(this, basicallyService::class.java)
        serviceIntent.putExtra("Switch", message)
        startService(serviceIntent)
    }

    fun startservice(v: View?){
        service.start()
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {}
    fun stopService(v: View?) {
        val serviceIntent = Intent(this, basicallyService::class.java)
        stopService(serviceIntent)
    }

    external fun stringFromJNI(): String?
    external fun hello(): String?
}