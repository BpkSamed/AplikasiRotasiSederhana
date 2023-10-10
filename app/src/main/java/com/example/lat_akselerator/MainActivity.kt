package com.example.lat_akselerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        // Mendapatkan data akselerometer dari event
        val xAcceleration = event?.values?.get(0)
        val yAcceleration = event?.values?.get(1)
        val zAcceleration = event?.values?.get(2)

        val xcoor = findViewById<TextView>(R.id.xcoor)
        val ycoor = findViewById<TextView>(R.id.ycoor)
        val zcoor = findViewById<TextView>(R.id.zcoor)
        xcoor.text = "X: $xAcceleration\n"
        ycoor.text = "Y: $yAcceleration\n"
        zcoor.text = "Z: $zAcceleration\n"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle perubahan akurasi sensor jika diperlukan
    }

    override fun onResume() {
        super.onResume()

        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }
}