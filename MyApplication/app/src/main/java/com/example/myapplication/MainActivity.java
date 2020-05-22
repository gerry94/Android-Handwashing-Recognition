package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ConnectionReceiver receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new ConnectionReceiver();
        intentFilter = new IntentFilter("home_proximity");
    }

    public void sendMessage(View v) {
        //Intent myIntent = new Intent("home_proximity");
        //PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0,myIntent, 0);
        Intent intent = new Intent("home_proximity");
        sendBroadcast(intent);
        Log.d("MSG","Intent sent in broadcast "+intent.getAction());
    }
}