package com.example.chulili.forgroundservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chulili on 2017/5/11.
 */

public class SampleBackgroundSecondService extends Service {
    private static final String TAG = SampleBackgroundSecondService.class.getSimpleName();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startService(new Intent(SampleBackgroundSecondService.this, SampleBackgroundThirdServiceeywqityeoqurpwjcksdhfdsljajdfslfj.class));
            Log.d(TAG, "handleMessage: stop foreground.");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

//        new Thread(){
//            @Override
//            public void run() {
//                Log.d(TAG, "start thread:" + Thread.currentThread().getId());
//                SystemClock.sleep(1000 * 60 * 5);
//                handler.sendEmptyMessage(1);
//            }
//        }.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
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
