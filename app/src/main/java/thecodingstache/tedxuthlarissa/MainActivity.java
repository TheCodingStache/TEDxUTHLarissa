package thecodingstache.tedxuthlarissa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.gif);
        mTextView = findViewById(R.id.welcometotedx);
        Glide.with(this).load(R.drawable.tedback).into(imageView);


//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("Menu", R.drawable.asl_pathmorph_drawer));
        menuItemList.add(new MenuItem("Schedule", R.drawable.calendar));
        menuItemList.add(new MenuItem("Speakers", R.drawable.speaker));
        menuItemList.add(new MenuItem("Our Team", R.drawable.team));
        menuItemList.add(new MenuItem("Map", R.drawable.maps));
        menuItemList.add(new MenuItem("Venue", R.drawable.venue));
        menuItemList.add(new MenuItem("Partners", R.drawable.sponsors));
        menuItemList.add(new MenuItem("Profile", R.drawable.profile));


//        sNavigationDrawer.setMenuItemList(menuItemList);
        fragmentClass = MenuFragment.class;
//        sNavigationDrawer.setNavigationDrawerBackgroundColor(Color.TRANSPARENT);

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
                    imageView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.speakers_item:
                    selectedFragment = new SpeakersFragment();
                    imageView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.team_item:
                    selectedFragment = new TeamFragment();
                    imageView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.map_item:
                    selectedFragment = new MapFragment();
                    imageView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                    break;
                case R.id.profile_item:
                    selectedFragment = new ProfileFragment();
                    imageView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                    selectedFragment).commit();

            return true;
        }
    };
}


//        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
//            @Override
//            public void onMenuItemClicked(int position) {
//                switch (position) {
//                    case 0: {
//                        fragmentClass = MenuFragment.class;
//                        break;
//                    }
//                    case 1: {
//                        fragmentClass = ScheduleFragment.class;
//                        break;
//                    }
//                    case 2: {
//                        fragmentClass = SpeakersFragment.class;
//                        break;
//
//                    }
//                    case 3: {
//                        fragmentClass = TeamFragment.class;
//                        break;
//
//                    }
//                    case 4:
//                        fragmentClass = MapFragment.class;
//                        break;
//
//                    case 5: {
//                        fragmentClass = VenueFragment.class;
//                        break;
//
//                    }
//                    case 6: {
//                        fragmentClass = PartnersFragment.class;
//                        break;
//
//                    }
//                    case 7: {
//                        fragmentClass = ProfileFragment.class;
//                        break;
//                    }
//                }
//
//                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
//                    @Override
//                    public void onDrawerOpening() {
//
//                    }
//
//                    @Override
//                    public void onDrawerClosing() {
//                        try {
//                            mFragment = (Fragment) fragmentClass.newInstance();
//                        } catch (IllegalAccessException | InstantiationException e) {
//                            e.printStackTrace();
//                        }
//                        if (mFragment != null) {
//                            FragmentManager fragmentManager = getSupportFragmentManager();
//                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, mFragment).commit();
//
//                        }
//                    }
//
//                    @Override
//                    public void onDrawerOpened() {
//
//                    }
//
//                    @Override
//                    public void onDrawerClosed() {
//
//                    }
//
//                    @Override
//                    public void onDrawerStateChanged(int newState) {
//
//                    }
//                });
//            }
//
//        });


