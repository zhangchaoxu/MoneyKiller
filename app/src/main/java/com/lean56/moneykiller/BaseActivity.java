package com.lean56.moneykiller;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

/**
 * Base Activity of all activities of Application
 *
 * @author Charles
 */
public class BaseActivity extends AppCompatActivity {

    private final static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the sys bar include status bar and navigation bar
        matchSystemBarWithActionBar();

        Log.d(getClass().getSimpleName(), TAG + "Base.onCreate...");
    }

    // [+]translucent system bar

    /**
     * Apply background tinting to the Android system UI when using KitKat translucent modes.
     * see {https://github.com/jgilfelt/SystemBarTint}
     */
    private void matchSystemBarWithActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);

            setSystemBarTint(true, R.color.color_primary_dark, false, 0);
        }
    }

    /**
     * set the system bar tint, include statusBar and navigation
     *
     * @param statusBarTintEnabled     enable status bar tine
     * @param statusBarResId           res id of status bar tint
     * @param navigationNarTintEnabled enable navigation bar tint
     * @param navigationBarResId       res id of navigation bar tint
     */
    protected void setSystemBarTint(boolean statusBarTintEnabled, int statusBarResId, boolean navigationNarTintEnabled, int navigationBarResId) {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        if (statusBarTintEnabled) {
            // set the status bar tint
            tintManager.setStatusBarTintEnabled(true);
            if (0 != statusBarResId) {
                tintManager.setStatusBarTintResource(statusBarResId);
            }
        }

        if (navigationNarTintEnabled) {
            // set the navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            if (0 != navigationBarResId) {
                tintManager.setNavigationBarTintResource(navigationBarResId);
            }
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    // [-]translucent system bar

    // [+] Umeng Analytics
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    // [-] Umeng Analytics
}
