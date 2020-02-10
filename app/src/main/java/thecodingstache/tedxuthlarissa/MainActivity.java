package thecodingstache.tedxuthlarissa;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

import thecodingstache.tedxuthlarissa.Fragment.MapFragment;
import thecodingstache.tedxuthlarissa.Fragment.MenuFragment;
import thecodingstache.tedxuthlarissa.Fragment.PartnersFragment;
import thecodingstache.tedxuthlarissa.Fragment.ProfileFragment;
import thecodingstache.tedxuthlarissa.Fragment.ScheduleFragment;
import thecodingstache.tedxuthlarissa.Fragment.SpeakersFragment;
import thecodingstache.tedxuthlarissa.Fragment.TeamFragment;
import thecodingstache.tedxuthlarissa.Fragment.VenueFragment;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("Menu",R.drawable.asl_pathmorph_drawer));
        menuItemList.add(new MenuItem("Schedule", R.drawable.calendar));
        menuItemList.add(new MenuItem("Speakers", R.drawable.speaker));
        menuItemList.add(new MenuItem("Our Team", R.drawable.team));
        menuItemList.add(new MenuItem("Map", R.drawable.maps));
        menuItemList.add(new MenuItem("Venue", R.drawable.venue));
        menuItemList.add(new MenuItem("Partners", R.drawable.sponsors));
        menuItemList.add(new MenuItem("Profile", R.drawable.profile));
        sNavigationDrawer.setMenuItemList(menuItemList);
        fragmentClass = MenuFragment.class;
        sNavigationDrawer.setNavigationDrawerBackgroundColor(Color.TRANSPARENT);

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
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position) {
                    case 0: {
                        fragmentClass = MenuFragment.class;
                        break;
                    }
                    case 1: {
                        fragmentClass = ScheduleFragment.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = SpeakersFragment.class;
                        break;

                    }
                    case 3: {
                        fragmentClass = TeamFragment.class;
                        break;

                    }
                    case 4:
                        fragmentClass = MapFragment.class;
                        break;

                    case 5: {
                        fragmentClass = VenueFragment.class;
                        break;

                    }
                    case 6: {
                        fragmentClass = PartnersFragment.class;
                        break;

                    }
                    case 7: {
                        fragmentClass = ProfileFragment.class;
                        break;
                    }
                }

                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                    @Override
                    public void onDrawerOpening() {

                    }

                    @Override
                    public void onDrawerClosing() {
                        try {
                            mFragment = (Fragment) fragmentClass.newInstance();
                        } catch (IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                        if (mFragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, mFragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });
            }

        });
    }
}
