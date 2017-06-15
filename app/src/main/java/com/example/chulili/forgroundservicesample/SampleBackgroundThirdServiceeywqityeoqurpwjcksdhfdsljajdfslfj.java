package com.example.chulili.forgroundservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chulili on 2017/5/12.
 */

public class SampleBackgroundThirdServiceeywqityeoqurpwjcksdhfdsljajdfslfj extends Service {
    private static final String TAG = SampleBackgroundThirdServiceeywqityeoqurpwjcksdhfdsljajdfslfj.class.getSimpleName();

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
                Log.d(TAG, "thread end");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
