package com.lean56.moneykiller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        //matchSystemBarWithActionBar();

        Log.d(getClass().getSimpleName(), TAG + "Base.onCreate...");
    }

    /*@Override
    public void init(Bundle savedInstanceState) {
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(),"NeoKree","neokree@gmail.com", R.drawable.ic_launcher, R.drawable.ic_launcher);
        this.addAccount(account);

       *//* MaterialAccount account2 = new MaterialAccount(this.getResources(),"Hatsune Miky","hatsune.miku@example.com",R.drawable.photo2,R.drawable.mat2);
        this.addAccount(account2);

        MaterialAccount account3 = new MaterialAccount(this.getResources(),"Example","example@example.com",R.drawable.photo,R.drawable.mat3);
        this.addAccount(account3);*//*

        // create sections
        this.addSection(newSection("Section 1", new MainListFragment()));
        this.addSection(newSection("Section 2",new MainListFragment()));
        //this.addSection(newSection("Section 3", R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
        //this.addSection(newSection("Section", R.drawable.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));

        // create bottom section
        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_launcher, new Intent(this, SettingActivity.class)));
    }*/



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
