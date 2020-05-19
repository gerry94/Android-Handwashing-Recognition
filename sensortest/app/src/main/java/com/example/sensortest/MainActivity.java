package com.example.sensortest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope;
    TextView txt, txt2;
    View view;

    //contains the results of the classifier during a time window in which the user might be washing hands
    List<Double> accelVector = new ArrayList<>(Collections.nCopies(10, 0.0));
    int accelInserts = 0;

    List<Double> gyroVector = new ArrayList<>(Collections.nCopies(10, 0.0));
    int gyroInserts = 0;

    final int SAMPLING = 2000000; //microseconds corresponding to 50Hz

    //provare come cambia il classificatore al variare di THRESHOLD e OVERLAP
    final int THRESHOLD = 7;
    final double OVERLAP = 0.5; //50% overlapped windows

    final double ACC_WEIGHT = 0.96;
    final double GYR_WEIGHT = 0.8;
    final int WINDOW_SIZE = 50;

    MotionSensor accelData = new MotionSensor();
    MotionSensor gyrData = new MotionSensor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.getWindow().getDecorView();

        txt = (TextView) findViewById(R.id.textBox);
        txt2 = (TextView) findViewById(R.id.textBox2);

        txt.setText("ACC = ");
        txt2.setText("GYR = ");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(MainActivity.this, accelerometer, SAMPLING); //sampling expressed in micro-seconds
        sensorManager.registerListener(MainActivity.this, gyroscope, SAMPLING);

    }

    private void overlap(List<Double> list, double window) {
        List<Double> app = new ArrayList<>();

        for(int i = (int)Math.floor(list.size()*window); i<list.size(); i++)
            app.add(list.get(i));

        list.clear();

        for(double num: app)
            list.add(num);
    }

    public void print(TextView t, String beginning, List<Double> l) {
        t.setText(beginning+" = [");

        for(double num: l)
            t.append((int)num+", ");
        t.append("]");
    }

    private double computeWeightedSums() {
        double s1 = 0, s2 = 0;
        for(double num: accelVector)
            s1 = s1+num;
        for(double num: gyroVector)
            s2 = s2+num;

        return ( (s1*ACC_WEIGHT)+(s2*GYR_WEIGHT) );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelData.add(event.values[0], event.values[1], event.values[2]);

            if (accelData.getNumData() == WINDOW_SIZE) {
                accelData.computeFeatures();
                try {
                    Double result = WekaAccelClassifier.classify(accelData.getFeatures());

                    //inserting at the end, popping the first element
                    accelVector.add(result);
                    accelVector.remove(0);
                    accelInserts++;

                    //only for debug purposes
                    print(txt, "ACC", accelVector);

                    if(accelInserts == OVERLAP*10) {
                        if( computeWeightedSums()/2 >= THRESHOLD ) {
                            view.setBackgroundColor(Color.argb(255, 0, 255, 0));
                            //accelInserts = 0;
                        } else view.setBackgroundColor(Color.argb(255, 255, 0, 0));
                        accelInserts = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                accelData.flush();
            }
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyrData.add(event.values[0], event.values[1], event.values[2]);

            if (gyrData.getNumData() == WINDOW_SIZE) {
                gyrData.computeFeatures();
                try {
                    Double result = WekaGyroClassifier.classify(gyrData.getFeatures());
                    gyroVector.add(result);
                    gyroVector.remove(0);
                    gyroInserts++;

                    //only for debug purposes
                    print(txt2, "GYR", gyroVector);
                    if(gyroInserts == OVERLAP*10) {
                        if( computeWeightedSums()/2 >= THRESHOLD ) {
                            view.setBackgroundColor(Color.argb(255, 0, 255, 0));
                        } else view.setBackgroundColor(Color.argb(255, 255, 0, 0));
                        gyroInserts = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gyrData.flush();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}

/*
accx = (TextView) findViewById(R.id.xValue);
        accy = (TextView) findViewById(R.id.yValue);
        accz = (TextView) findViewById(R.id.zValue);

        gyrx = (TextView) findViewById(R.id.xGyrValue);
        gyry = (TextView) findViewById(R.id.yGyrValue);
        gyrz = (TextView) findViewById(R.id.zGyrValue);
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
    }

    String data = event.values[0] +","+ event.values[1] +","+ event.values[2];
            try {
                FileOutputStream f = new FileOutputStream(accelFile, true);
                OutputStreamWriter o = new OutputStreamWriter(f);
                o.append(data+"\n");
                o.close();
                f.close();
            } catch(IOException e) { e.printStackTrace();}
 */