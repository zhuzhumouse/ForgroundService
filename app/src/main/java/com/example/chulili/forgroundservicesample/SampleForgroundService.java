package com.example.chulili.forgroundservicesample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chulili on 2017/5/10.
 */

public class SampleForgroundService extends Service {
    private static final String TAG = SampleForgroundService.class.getSimpleName();
    private int notification_id = 20;
    private NotificationManager notifManager = null;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: stop foreground.");
            if (msg.what == 1) {
                Log.d(TAG, "msg.what == 1: " + msg.toString());
                stopForeground(false);
                notifManager.cancel(notification_id);
            }
//            if (msg.what == 2) {
//                startService(new Intent(SampleForgroundService.this, SampleForgroundThirdService.class));
//            }
//            notifManager.cancelAll();
        }
    };

    public class LocalBinder extends Binder {
        SampleForgroundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SampleForgroundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        notifManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        new Thread(){
//            @Override
//            public void run() {
//                Log.d(TAG, "start thread:" + Thread.currentThread().getId());
//                SystemClock.sleep(1000 * 60 * 1);
//                handler.sendEmptyMessage(1);
//            }
//        }.start();

        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent nfIntent = new Intent(this, MainActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(null)
                .setContentTitle(null)
                .setContentText(null)
                .setOngoing(false)
                .setWhen(0);

        Notification notification = builder.build();
        startForeground(notification_id, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
//        flags = Service.START_FLAG_REDELIVERY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
//        sendOrderedBroadcast(new Intent(ServiceDestroyReceiver.SERVICE_DESTROY_ACTION), null);
        Log.d(TAG, "onDestroy");
    }
}
