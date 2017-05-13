package youtube.android.sec.com.ipl;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shiv on 4/11/2017.
 */

public class IPLApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
