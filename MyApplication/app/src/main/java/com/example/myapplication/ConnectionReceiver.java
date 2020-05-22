package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionReceiver extends BroadcastReceiver {

    String key = null;
    Boolean entering = true;

    final int MIN_ACTIVITY_TIME = 10; //time that the user SHOULD take to perform the desired activity (in seconds)

    //contains the results of the classifier during a time window in which the user might be washing hands
    List<Double> accelVector = new ArrayList<>(Collections.nCopies(MIN_ACTIVITY_TIME, 0.0));
    int accelInserts = 0;

    List<Double> gyroVector = new ArrayList<>(Collections.nCopies(MIN_ACTIVITY_TIME, 0.0));
    int gyroInserts = 0;

    //provare come cambia il classificatore al variare di THRESHOLD e OVERLAP
    final double THRESHOLD = 0.7*MIN_ACTIVITY_TIME; //70%
    final double OVERLAP = 0.5; //50% overlapped windows

    final double ACC_WEIGHT = 0.96;
    final double GYR_WEIGHT = 0.8;
    final int WINDOW_SIZE = 50;

    MotionSensor accelData = new MotionSensor();
    MotionSensor gyrData = new MotionSensor();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("MSG","Ricevuto intent..."+intent.getAction());

        if(intent.getAction().equals("home_proximity")) {
            Toast.makeText(context, "home_proximity is received", Toast.LENGTH_LONG).show();
            Log.d("TEST","home_proximity ricevuto");
            //controllare se ritorno a casa o se sta andando via con campo KEY_PROXIMITY_ENTERING (è lo stesso intent)
            key = LocationManager.KEY_PROXIMITY_ENTERING;
            entering = intent.getBooleanExtra(key, true);

            //se rientro a casa
            if(entering) {
                Log.d("TEST","Rientrato a casa");
                try {
                    //read csv file (first 15mins) and classify
                    BufferedReader br = new BufferedReader(new FileReader("data/accelData.csv"));
                    BufferedReader br2 = new BufferedReader(new FileReader("data/gyrData.csv"));
                    String separator = ",", line = br.readLine(), line2 = br2.readLine();

                    int linesRead = 0;
                    //90000 lines = 15mins worth of data for both sensors (45000 lines each)
                    while( (linesRead < 90000) && line != null && line2 != null)
                    {
                        String[] coord = line.split(separator);
                        accelData.add(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), Double.parseDouble(coord[2]));

                        coord = line2.split(separator);
                        gyrData.add(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), Double.parseDouble(coord[2]));

                        if ( accelData.getNumData() == WINDOW_SIZE)
                        {
                            accelData.computeFeatures();
                            try {
                                Double result = WekaAccelClassifier.classify(accelData.getFeatures());

                                //inserting at the end, popping the first element
                                accelVector.add(result);
                                accelVector.remove(0);
                                accelInserts++;

                                if(accelInserts == OVERLAP*MIN_ACTIVITY_TIME) {
                                    double s1 = 0, s2 = 0;
                                    for(double num: accelVector)
                                        s1 = s1+num;
                                    for(double num: gyroVector)
                                        s2 = s2+num;

                                    if( ( (s1*ACC_WEIGHT)+(s2*GYR_WEIGHT) )/2 >= THRESHOLD ) {
                                        //send intent
                                        sendIntent(context);
                                        break;
                                    }
                                    accelInserts = 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            accelData.flush();
                        }
                        if(gyrData.getNumData() == WINDOW_SIZE) {
                            gyrData.computeFeatures();
                            try {
                                Double result = WekaGyroClassifier.classify(gyrData.getFeatures());

                                //inserting at the end, popping the first element
                                gyroVector.add(result);
                                gyroVector.remove(0);
                                gyroInserts++;;

                                if(gyroInserts == OVERLAP*MIN_ACTIVITY_TIME) {
                                    double s1 = 0, s2 = 0;
                                    for(double num: accelVector)
                                        s1 = s1+num;
                                    for(double num: gyroVector)
                                        s2 = s2+num;

                                    if( ( (s1*ACC_WEIGHT)+(s2*GYR_WEIGHT) )/2 >= THRESHOLD ) {
                                        //send intent
                                        sendIntent(context);
                                        break;
                                    }
                                    gyroInserts = 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            gyrData.flush();
                        }
                        line = br.readLine();
                        line2 = br2.readLine();
                    }
                } catch(IOException e) { e.printStackTrace(); }
            } else {
                //se invece sta uscendo da casa, non faccio nulla e mi rimetto in ricezione
                Log.d("TEST","utente uscito da casa");
                Toast.makeText(context, "Utente uscito da casa", Toast.LENGTH_LONG).show();
            }
        }
        else {
            //Ricevuto intent non di interesse
            Log.d("TEST","Altro intent ricevuto");
            Toast.makeText(context, "Some other action received", Toast.LENGTH_LONG).show();
        }
    }

    public void sendIntent(Context context) {
        Intent intentWashing = new Intent("handWashed");
        context.sendBroadcast(intentWashing);
        Log.d("MSG","Intent handWashed");
    }
}