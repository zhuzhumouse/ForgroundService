package com.example.chulili.forgroundservicesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
    }

    public void startServiceForground(View view) {

//        sendBroadcast(new Intent("com.example.chulili.forgroundservice.servicedestroy"));

//        startService(new Intent(MainActivity.this, SampleForgroundThirdService.class));
//        startService(new Intent(MainActivity.this, SampleForgroundService.class));
//        startService(new Intent(MainActivity.this, SampleForegroundSecondService.class));
        bindService(new Intent(MainActivity.this, SampleForgroundService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
        startService(new Intent(MainActivity.this, SampleBackgroundServiceafjaispdfjasdeljflkd.class));
//        startService(new Intent(MainActivity.this, SampleForgroundService.class));
        startService(new Intent(MainActivity.this, SampleBackgroundThirdServiceeywqityeoqurpwjcksdhfdsljajdfslfj.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void startServiceActivity(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

}
