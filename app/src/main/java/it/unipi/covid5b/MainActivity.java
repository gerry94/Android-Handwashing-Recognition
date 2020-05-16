package it.unipi.covid5b;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
//import android.os.CountDownTimer;
import android.util.Log;
import java.util.Timer;
import it.unipi.covid5b.BroadcastReceiver;
import it.unipi.covid5b.CountDownTimer;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ricezione dell'intent
        //new MyReceiver();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BroadcastReceiver.BroadcastReceiver.MyBroadcastReceiver(getApplicationContext(), /*intent ricevuto*/);
        //Timer initialization: 15 minutes, tick every 1 second
        new CountDownTimer(9000000, 1000) {
        }.start();
    }


}




