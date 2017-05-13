package android.sec.com.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "roadcastreceivedb", Toast.LENGTH_SHORT).show();
        Log.i("shiv" ,"hi shiv");

    }
}
