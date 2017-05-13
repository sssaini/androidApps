package youtube.android.sec.com.ipl;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by shiv on 4/8/2017.
 */

public class Utils {

    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }


        mProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }
}
