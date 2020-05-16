package it.unipi.covid5b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ricezione dell'intent
        //new MyReceiver();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Timer initialization
        new CountDownTimer(9000000, 1000) { //timer di 15 minuti, tick di 1 secondo
            @Override
            public void onTick(long millisUntilFinished) {
                //check classificatore
            }
            public void onFinish() {
                Log.d("MY_WASH", "Non ti sei lavato le mani");
                /*
                Se arrivo qui, scade il timer, non è stato effettuato il lavaggio delle mani
                Quindi mando l'intent broadcast
                MainActivity.send();
                */
                send();
            }
        }.start();
    }
    public void send() {
        Intent intent=new Intent(getApplicationContext(), /*risultato del classificatore*/);
        intent.setFlags(0);
        sendBroadcast(intent);
    }
}

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MtBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder msg = new StringBuilder();
        msg.append("Action: " + intent.getAction() + "\n");
        Log.d(TAG, "I just received home signal");
    }




