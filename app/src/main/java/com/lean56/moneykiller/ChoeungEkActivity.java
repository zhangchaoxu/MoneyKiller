package com.lean56.moneykiller;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.lean56.moneykiller.ui.fragment.CalculatorFragment;
import com.lean56.moneykiller.ui.fragment.MainListFragment;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * ChoeungEk-TheKillingFields(http://en.wikipedia.org/wiki/Choeung_Ek)
 *
 * @author Charles
 */
public class ChoeungEkActivity extends MaterialNavigationDrawer {

    private final static String TAG = ChoeungEkActivity.class.getSimpleName();
    private Context mContext;

    /**
     * view res
     */
    private Toolbar mToolbar;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavigationTitles;

    /**
     * location
     */
    private LocationManagerProxy mLocationManagerProxy;
    private AMapLocationListener mLocationListener;

    @Override
    public void init(Bundle savedInstanceState) {
        this.addSection(newSection("工资", new CalculatorFragment()));
        this.addSection(newSection("年终奖",new MainListFragment()));

        // create bottom section
        this.addBottomSection(newSection(getString(R.string.setting), R.drawable.ic_setting, new Intent(this, SettingActivity.class)));
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choeung_ek);
        mContext = this;

        mTitle = mDrawerTitle = getTitle();
        mNavigationTitles = getResources().getStringArray(R.array.navigation_array);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // improve performance by indicating the list if fixed size.
        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new DrawerNavigationAdapter(mNavigationTitles, new DrawerNavigationAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                selectItem(position);
            }
        }));

        // enable ActionBar app icon to behave as action to toggle nav drawer
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        activateLocation();
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choeung_ek, menu);
        return true;
    }

    *//* Called whenever we call invalidateOptionsMenu() *//*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /// The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_about:
                Toast.makeText(this, "about", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = MainListFragment.newInstance(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        // update selected item title, then close the drawer
        setTitle(mNavigationTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    /*@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }*/

    // [+] Location Listener

    /**
     * activate Location
     * see {http://lbs.amap.com/api/android-location-sdk/guide/location/}
     */
    private void activateLocation() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
                    handleLocation(amapLocation);
                    deactivateLocation();
                } else {
                    Log.e(TAG, "Amap Location ERR:" + amapLocation.getAMapException().getErrorCode());
                }
            }

            @Override
            public void onLocationChanged(Location location) {}

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };
        mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, -1, 15, mLocationListener);
        mLocationManagerProxy.setGpsEnable(false);
    }

    private void handleLocation(AMapLocation location) {
        Toast.makeText(this, "当前城市：" + location.getCity() + "-" + location.getCityCode(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "当前位置：" + location.getLatitude() + "-" + location.getLongitude());
    }

    /**
     * stop location
     */
    private void deactivateLocation() {
        if (null != mLocationManagerProxy) {
            mLocationListener = null;

            mLocationManagerProxy.removeUpdates(mLocationListener);
            mLocationManagerProxy.destroy();
            mLocationManagerProxy = null;
        }
    }
    // [-] Location Listener

    @Override
    protected void onPause() {
        super.onPause();
        deactivateLocation();
    }

}
