package com.example.chulili.forgroundservicesample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chulili on 2017/5/10.
 */

public class SampleForgroundThirdService extends Service{
    private static final String TAG = SampleForgroundThirdService.class.getSimpleName();
    private int notification_id = 20;
    private NotificationManager notifManager = null;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: stop foreground");
            stopForeground(Service.STOP_FOREGROUND_DETACH);//
//            notifManager.cancel(notification_id);
//            stopSelf();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        notifManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        new Thread(){
            @Override
            public void run() {
                Log.d(TAG, "start thread:" + Thread.currentThread().getId());
                SystemClock.sleep(1000 * 60 * 2);
                handler.sendEmptyMessage(1);
            }
        }.start();

        // 在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_android_black_24dp)) // 设置下拉列表中的图标(大图标)
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setTicker(null)
                .setContentTitle(null)
                .setContentText(null)
                .setOngoing(false)
                .setWhen(0); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(notification_id, notification);
        notifManager.notify(notification_id, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        Log.d(TAG, "onDestroy");
    }
}
