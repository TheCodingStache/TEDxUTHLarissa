package thecodingstache.tedxuthlarissa.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CoreTeamFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CurrationFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.DirectorsFragment;
import thecodingstache.tedxuthlarissa.ListAdapter.PageAdapter;
import thecodingstache.tedxuthlarissa.ListAdapter.ScheduleAdapter;
import thecodingstache.tedxuthlarissa.ListAdapter.TeamAdapter;
import thecodingstache.tedxuthlarissa.R;
import thecodingstache.tedxuthlarissa.Schedule.MainStage;

public class TeamFragment extends Fragment {
    private TabLayout mTabLayout;
    private TeamAdapter adapter;
    private ViewPager mViewPager;
    private String mainStage = "Curration Team";
    private String greenRoom = "Directors";
    private String workshops = "Core Team";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        adapter = new TeamAdapter(getFragmentManager());
        adapter.addFragment(new CurrationFragment(), mainStage);
        adapter.addFragment(new DirectorsFragment(), greenRoom);
        adapter.addFragment(new CoreTeamFragment(), workshops);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;

//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                mViewPager.setCurrentItem(tab.getPosition());
//                mPagerAdapter.notifyDataSetChanged();
//                if (tab.getPosition() == 0) {
//                    mPagerAdapter.notifyDataSetChanged();
//                }
//                if (tab.getPosition() == 1) {
//                    mPagerAdapter.notifyDataSetChanged();
//                }
//                if (tab.getPosition() == 2) {
//                    mPagerAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        return view;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//    }
    }
}

