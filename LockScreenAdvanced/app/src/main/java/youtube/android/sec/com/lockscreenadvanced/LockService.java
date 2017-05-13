package youtube.android.sec.com.lockscreenadvanced;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.util.Log.*;

/**
 * Created by shiv on 4/24/2017.
 */

public class LockService extends Service {
    private BroadcastReceiver mReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        Log.i("shiv" ,"Service create");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver =  new LockReceiver();
        registerReceiver(mReceiver,filter);
        startForeground();
        return START_STICKY;
    }

    private void startForeground() {

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("LockVocab")
                .setTicker("LockVocab")
                .setContentText("Running")
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentIntent(null)
                .setOngoing(true)
                .build();
        startForeground(9999,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
