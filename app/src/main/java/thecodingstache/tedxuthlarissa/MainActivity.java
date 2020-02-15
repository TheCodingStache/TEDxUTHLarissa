package thecodingstache.tedxuthlarissa;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import thecodingstache.tedxuthlarissa.Fragment.MapFragment;
import thecodingstache.tedxuthlarissa.Fragment.ProfileFragment;
import thecodingstache.tedxuthlarissa.Fragment.ScheduleFragment;
import thecodingstache.tedxuthlarissa.Fragment.SpeakersFragment;
import thecodingstache.tedxuthlarissa.Fragment.TeamFragment;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    public static Fragment mFragment;
    private ImageView imageView;
    private TextView mTextView;
    NavController navController;
    DrawerLayout drawer;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imageView = findViewById(R.id.gif);
//        mTextView = findViewById(R.id.welcometotedx);
//        Glide.with(this).load(R.drawable.tedback).into(imageView);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this, R.id.main_fragment);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_navigate_up_description);
        drawer.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        appBarConfiguration = new AppBarConfiguration.Builder()
                .setDrawerLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        fragmentClass = ScheduleFragment.class;
        try {
            mFragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        if (mFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frameLayout, mFragment).commit();
        }

    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
            Fragment selectedFragment = new Fragment();
            switch (item.getItemId()) {
                case R.id.schedule_item:
                    selectedFragment = new ScheduleFragment();
//                    imageView.setVisibility(View.GONE);
//                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.speakers_item:
                    selectedFragment = new SpeakersFragment();
//                    imageView.setVisibility(View.GONE);
//                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.team_item:
                    selectedFragment = new TeamFragment();
//                    imageView.setVisibility(View.GONE);
//                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.map_item:
                    selectedFragment = new MapFragment();
//                    imageView.setVisibility(View.GONE);
//                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.profile_item:
                    selectedFragment = new ProfileFragment();
//                    imageView.setVisibility(View.GONE);
//                    mTextView.setVisibility(View.GONE);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                    selectedFragment).commit();

            return true;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            //You may not want to open the drawer on swipe from the left in this case
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // Remove hamburger
            mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            //You must regain the power of swipe for the drawer.
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mActionBarDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }
}

