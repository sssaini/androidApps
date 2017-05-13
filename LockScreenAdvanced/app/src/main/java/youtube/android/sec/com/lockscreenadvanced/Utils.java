package youtube.android.sec.com.lockscreenadvanced;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by shiv on 4/23/2017.
 */

public class Utils {

    private OverlayDialog mOverlayDialog;
    private onLockStatusChangedListener mLockStatusChangedListener;

    public interface onLockStatusChangedListener {
        public void onLockStatusChanged(boolean isLocked);
    }

    public Utils() {
        reset();
    }

    public void reset() {
        if (mOverlayDialog != null) {
            mOverlayDialog.dismiss();
            mOverlayDialog = null;
        }
    }

    public void unlock() {
        if (mOverlayDialog != null) {
            mOverlayDialog.dismiss();
            mOverlayDialog = null;
            if (mLockStatusChangedListener != null) {
                mLockStatusChangedListener.onLockStatusChanged(false);
            }
        }
    }

    public void lock(Activity activity) {

        if (mOverlayDialog == null) {
            mOverlayDialog = new OverlayDialog(activity);
            mOverlayDialog.show();
            mLockStatusChangedListener = (onLockStatusChangedListener) activity;
        }
    }

    private static class OverlayDialog extends AlertDialog {

        protected OverlayDialog(Activity activity) {
            super(activity, R.style.OverlayDialog);
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            params.dimAmount = 0.0F;
            params.width = 0;
            params.height = 0;
            params.gravity = Gravity.BOTTOM;
            getWindow().setAttributes(params);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, 0xffffff);
            setOwnerActivity(activity);
            setCancelable(false);
        }

        public final boolean dispatchTouchEvent(MotionEvent motionevent) {
            return true;
        }
    }

}
