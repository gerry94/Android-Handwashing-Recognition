package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionReceiver extends BroadcastReceiver {

    String key = null;
    Boolean entering = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("MSG","Ricevuto intent..."+intent.getAction());

        if(intent.getAction().equals("home_proximity")) {

            Toast.makeText(context, "home_proximity is received", Toast.LENGTH_LONG).show();

            //controllare se ritorno a casa o se sta andando via con campo KEY_PROXIMITY_ENTERING (è lo stesso intent)
            key = LocationManager.KEY_PROXIMITY_ENTERING;
            entering = intent.getBooleanExtra(key, false);

            //se rientro a casa
            if(entering) {
                //Se utente tornato a casa, carico file csv, starto il timer e procedo con la classificazione
                /*BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("c://prova.txt"));
                } catch (FileNotFoundException e) {
                    Log.d("ERROR", "Errore lettura file");
                    e.printStackTrace();
                }
                String line = null;
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (line != null) {
                    System.out.println(line);
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                */
                //Timer 5 secondi, tick ogni secondo
                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        //Check classifier
                        Log.d("TIMER", "Tick");
                    }

                    public void onFinish() {
                        //Timer expired
                        Log.d("TIMER", "End");
                    }
                }.start();
            }else{
                //se invece sta uscendo da casa, non faccio nulla e mi rimetto in ricezione
                Toast.makeText(context, "Utente uscito da casa", Toast.LENGTH_LONG).show();
            }

        }
        else {
            //Ricevuto intent non di interesse
            Toast.makeText(context, "Some other action received", Toast.LENGTH_LONG).show();
        }
    }
}