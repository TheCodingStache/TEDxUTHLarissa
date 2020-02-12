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

import thecodingstache.tedxuthlarissa.ListAdapter.PageAdapter;
import thecodingstache.tedxuthlarissa.R;

public class TeamFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabItem tab1, tab2, tab3;
    private PagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        mTabLayout = view.findViewById(R.id.tabLayout);
        getActivity().setTitle("Team");
        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        mViewPager = view.findViewById(R.id.viewPager);
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());
                mPagerAdapter.notifyDataSetChanged();
                if (tab.getPosition() == 0) {
                    mPagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    mPagerAdapter.notifyDataSetChanged();
                }
                if (tab.getPosition() == 2) {
                    mPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}

