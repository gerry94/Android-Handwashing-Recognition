package it.unipi.covid5b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("MSG","Ricevuto intent..."+intent.getAction());

        if(intent.getAction().equals("SOME_ACTION")) {
            //Utente tornato a casa, start timer
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
        }
        else {
            //Ricevuto intent non di interesse
            Toast.makeText(context, "Some other action received", Toast.LENGTH_LONG).show();
        }
    }
}