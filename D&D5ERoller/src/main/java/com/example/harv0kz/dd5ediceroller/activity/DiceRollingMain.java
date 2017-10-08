package com.example.harv0kz.dd5ediceroller.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.harv0kz.dd5ediceroller.R;
import com.example.harv0kz.dd5ediceroller.fragments.CharacterFragment;
import com.example.harv0kz.dd5ediceroller.fragments.CustomModFragment;
import com.example.harv0kz.dd5ediceroller.fragments.DiceRollingFragment;
import com.example.harv0kz.dd5ediceroller.fragments.LoggingFragment;
import com.example.harv0kz.dd5ediceroller.fragments.RNG;

public class DiceRollingMain extends AppCompatActivity
        implements OnItemSelectedListener {
//NavigationView.OnNavigationItemSelectedListener,

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    //Fragment tags
    public static int navItemIndex = 0;
    private static final String TAG_DICEROLLER = "diceroller";
    private static final String TAG_CHARACTERS = "characters";
    private static final String TAG_CUSTOMMODS = "custommods";
    private static final String TAG_LOGGING = "logging";
    public static String CURRENT_TAG = TAG_DICEROLLER;

    private String[] activityTitles;
    private boolean shouldLoadOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_rolling_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setupNavigationView();

        if (savedInstanceState==null){
            navItemIndex=0;
            CURRENT_TAG = TAG_DICEROLLER;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment(){
        selectNavMenu();

        if(getSupportFragmentManager().findFragmentByTag(CURRENT_TAG)!=null){
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getHomeFragment(){
        switch(navItemIndex){
            case 0:
                DiceRollingFragment diceRollingFragment = new DiceRollingFragment();
                return diceRollingFragment;
            case 1:
                CharacterFragment characterFragment = new CharacterFragment();
                return characterFragment;
            case 2:
                CustomModFragment customModFragment = new CustomModFragment();
                return customModFragment;
            case 3:
                LoggingFragment loggingFragment = new LoggingFragment();
                return loggingFragment;
            default:
                return new DiceRollingFragment();
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setupNavigationView(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.nav_diceroller:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_DICEROLLER;
                        break;
                    case R.id.nav_characters:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_CHARACTERS;
                        break;
                    case R.id.nav_custommods:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_CUSTOMMODS;
                        break;
                    case R.id.nav_logging:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_LOGGING;
                        break;
                    default:
                        navItemIndex = 0;
                }

                if(item.isChecked()){
                    item.setChecked(false);
                }
                else{
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(navItemIndex==0){
            getMenuInflater().inflate(R.menu.dice_rolling_page, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> whichSpinner, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
