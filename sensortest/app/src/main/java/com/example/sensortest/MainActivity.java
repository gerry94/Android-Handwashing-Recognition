package com.example.sensortest;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    final int SAMPLING = 2000000; //microseconds
    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope;
    TextView accx, accy, accz, gyrx, gyry, gyrz;
    View view;
    //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    //File accelFile = new File(path, "accelData.csv");
    //File gyrFile = new File(path, "gyrData.csv");

    MotionSensor accelData = new MotionSensor();
    MotionSensor gyrData = new MotionSensor();

    int numAccelData = 0, numGyrData = 0;

    WekaAccelClassifier accelClassifier = new WekaAccelClassifier();
    WekaGyroClassifier gyroClassifier = new WekaGyroClassifier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.getWindow().getDecorView();

        accx = (TextView) findViewById(R.id.textBox);
        /*accy = (TextView) findViewById(R.id.yValue);
        accz = (TextView) findViewById(R.id.zValue);

        gyrx = (TextView) findViewById(R.id.xGyrValue);
        gyry = (TextView) findViewById(R.id.yGyrValue);
        gyrz = (TextView) findViewById(R.id.zGyrValue);*/

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(MainActivity.this, accelerometer, SAMPLING); //sampling expressed in micro-seconds
        sensorManager.registerListener(MainActivity.this, gyroscope, SAMPLING);
/*
        try {
            FileOutputStream f = new FileOutputStream(accelFile, true);
            String data = "ACC_X,ACC_Y,ACC_Z";
            OutputStreamWriter o = new OutputStreamWriter(f);
            o.append(data+"\n");
            o.close();
            f.close();

            FileOutputStream ff = new FileOutputStream(gyrFile, true);
            data = "GYR_X,GYR_Y,GYR_Z";
            OutputStreamWriter oo = new OutputStreamWriter(ff);
            oo.append(data+"\n");
            oo.close();
            ff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            /*accy.setText("ACCEL_Y: " + event.values[1]);
            accz.setText("ACCEL_Z: " + event.values[2]);*/

            accelData.add(event.values[0], event.values[1], event.values[2]);
            numAccelData++;

            if(numAccelData == 50) {
                accelData.computeFeatures();
                try {
                    if(accelClassifier.classify(accelData.getFeatures()) > 0)
                        view.setBackgroundColor(Color.argb(255, 255, 0, 0));
                    else view.setBackgroundColor(Color.argb(255, 0, 255, 0));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                accelData.flush();
                numAccelData = 0;
            }
            /*String data = event.values[0] +","+ event.values[1] +","+ event.values[2];
            try {
                FileOutputStream f = new FileOutputStream(accelFile, true);
                OutputStreamWriter o = new OutputStreamWriter(f);
                o.append(data+"\n");
                o.close();
                f.close();
            } catch(IOException e) { e.printStackTrace();}*/
        } else if(sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyrData.add(event.values[0], event.values[1], event.values[2]);
            numGyrData++;

            if(numGyrData == 50) {
                gyrData.computeFeatures();
                try {
                    accx.setText("GYR: "+gyroClassifier.classify(gyrData.getFeatures()) );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gyrData.flush();
                numGyrData =0;
            }
            /*gyrx.setText("GYR_X: " + event.values[0]);
            gyry.setText("GYR_Y: " + event.values[1]);
            gyrz.setText("GYR_Z: " + event.values[2]);
            String data = event.values[0] +","+ event.values[1] +","+ event.values[2];
            try {
                FileOutputStream f = new FileOutputStream(gyrFile, true);
                OutputStreamWriter o = new OutputStreamWriter(f);
                o.append(data+"\n");
                o.close();
                f.close();
            } catch(IOException e) { e.printStackTrace();}*/
        }

        /* pseudo-codcie
        if(numData == 50)
            computeFeatures()
            getFeatures()
            flush()
         */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
