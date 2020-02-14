package thecodingstache.tedxuthlarissa;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import thecodingstache.tedxuthlarissa.Fragment.MenuFragment;
import thecodingstache.tedxuthlarissa.Fragment.ProfileFragment;
import thecodingstache.tedxuthlarissa.Fragment.ScheduleFragment;
import thecodingstache.tedxuthlarissa.Fragment.SpeakersFragment;
import thecodingstache.tedxuthlarissa.Fragment.TeamFragment;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment mFragment;
    private ImageView imageView;
    private TextView mTextView;
    NavController navController;
    DrawerLayout drawer;
    NavigationView navigationView;
    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;

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
        appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.main, R.id.secondary, R.id.third, R.id.forth})
                .setDrawerLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        fragmentClass = MenuFragment.class;
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

}

