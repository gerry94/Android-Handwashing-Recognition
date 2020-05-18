package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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
        intentFilter = new IntentFilter("SOME_ACTION");
    }

    public void sendMessage(View view) {
        Intent intent = new Intent("SOME_ACTION");
        sendBroadcast(intent);
        Log.d("MSG","Intent sent in broadcast");
    }
}
