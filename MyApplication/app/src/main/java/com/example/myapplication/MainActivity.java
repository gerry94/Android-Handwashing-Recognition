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
    WekaGyroClassifier gClassifier = null;
    WekaAccelClassifier aClassifier = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aClassifier = new WekaAccelClassifier();
        double result = 1234.0;
        // Array di features, 3 (XYZ) x 7 features estratte (mean, var....) = 21
        /*
        'mean_x'
        'max_x'
        'min_x'
        'std_x'
        'var_x'
        'ske_x'
        'zcr_x'
        'mean_y'
        'max_y'
        'min_y'
        'std_y'
        'var_y'
        'ske_y'
        'zcr_y'
        'mean_z'
        'max_z'
        'min_z'
        'std_z'
        'var_z'
        'ske_z'
        'zcr_z'
        'handwash'

         */
        Double arr[] = new Double[] {1.2, 5.6, 3.4, 2.9, 9.7, 1.2, 5.6, 3.4, 2.9, 9.7, 1.2, 5.6, 3.4, 2.9, 9.7, 1.2, 5.6, 3.4, 2.9, 9.7,2.3 };
        try {
            result = aClassifier.classify(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("Class", Double.toString(result) );


        receiver = new ConnectionReceiver();
        //intentFilter = new IntentFilter("SOME_ACTION");
    }

    //L'invio dell'intent in realtà sarà quando viene riconosciuto lavaggio mani
    public void sendMessage(View view) {
        Intent intentWashing = new Intent("handWashed");
        sendBroadcast(intentWashing);
        Log.d("MSG","Intent sent in broadcast");
    }
}
