package com.example.chulili.forgroundservicesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by chulili on 2017/5/11.
 */

public class ServiceDestroyReceiver extends BroadcastReceiver {
    public static final String SERVICE_DESTROY_ACTION = "com.example.chulili.forgroundservice.servicedestroy";
    private static final String TAG = ServiceDestroyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Log.d(TAG, "Broadcast: " + intent.getAction());
        }

//        if (intent != null && intent.getAction().equals(SERVICE_DESTROY_ACTION)) {
//            context.startService(new Intent(context, SampleForgroundService.class));
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(60*1000);
            }
        }).start();

    }
}
