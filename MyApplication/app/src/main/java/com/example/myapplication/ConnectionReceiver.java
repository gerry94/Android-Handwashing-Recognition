package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("MSG","Ricevuto intent..."+intent.getAction());

        //Utente tornato a casa
        if(intent.getAction().equals("SOME_ACTION")) {
            //Timer 5 secondi, tick ogni secondo
            new CountDownTimer(5000, 1000){
                public void onTick(long millisUntilFinished){
                    //Check classifier
                    Log.d("TIMER","Tick");
                }
                public  void onFinish(){
                    //Timer expired
                    Log.d("TIMER","End");
                }
            }.start();

            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
        }
        else {
            //Ricevuto intent non di interesse
            Toast.makeText(context, "Some other action received", Toast.LENGTH_LONG).show();
        }
    }
}