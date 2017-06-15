package com.example.chulili.forgroundservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chulili on 2017/5/12.
 */

public class SampleBackgroundFourService extends Service {
    private static final String TAG = SampleBackgroundFourService.class.getSimpleName();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: stop foreground.");
            if (msg.what == 1) {
                Log.d(TAG, "msg.what == 1: " + msg.toString());
                startService(new Intent(SampleBackgroundFourService.this, SampleForgroundThirdService.class));
            }
//            if (msg.what == 2) {
//                startService(new Intent(SampleForgroundService.this, SampleForgroundThirdService.class));
//            }
//            notifManager.cancelAll();
        }
    };

    public class LocalBinder extends Binder {
        SampleBackgroundFourService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SampleBackgroundFourService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread start");
                SystemClock.sleep(3*60*1000);
                handler.sendEmptyMessage(1);
                Log.d(TAG, "thread end");
            }
        }).start();

        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
