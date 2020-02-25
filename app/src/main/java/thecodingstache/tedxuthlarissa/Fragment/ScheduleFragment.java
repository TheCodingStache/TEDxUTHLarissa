package thecodingstache.tedxuthlarissa.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.DirectorsFragment;
import thecodingstache.tedxuthlarissa.ListAdapter.ScheduleAdapter;
import thecodingstache.tedxuthlarissa.R;
import thecodingstache.tedxuthlarissa.Schedule.MainStage;

public class ScheduleFragment extends Fragment {
    private ScheduleAdapter adapter;
    private TabLayout scheduleTabLayout;
    private ViewPager viewPager;
    private String mainStage = "Main Stage";
    private String greenRoom = "Green Room";
    private String workshops = "Workshops";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        viewPager = view.findViewById(R.id.request_orders_view_pager);
        scheduleTabLayout = view.findViewById(R.id.request_orders_tabs);
        scheduleTabLayout.setTabMode(TabLayout.MODE_FIXED);
        adapter = new ScheduleAdapter(getFragmentManager());
        adapter.addFragment(new MainStage(), mainStage);
        adapter.addFragment(new DirectorsFragment(), greenRoom);
        adapter.addFragment(new DirectorsFragment(), workshops);
        viewPager.setAdapter(adapter);
        scheduleTabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
