package android.mychatapp.com.mychatapplication.Dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by shiv on 3/20/2017.
 */

public class DialogUtil {

    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context){

        if (mProgressDialog == null) {

            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }
    public static  void dissMissDialog(){

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
             mProgressDialog.dismiss();
        }
        mProgressDialog=null;
    }

}
