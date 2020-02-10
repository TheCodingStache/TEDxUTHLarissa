package thecodingstache.tedxuthlarissa.ListAdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CoreTeamFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CurrationFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.DirectorsFragment;
import thecodingstache.tedxuthlarissa.Fragment.TeamFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PageAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                return new CurrationFragment();
            case 1:
                return new DirectorsFragment();
            case 2:
                return new CoreTeamFragment();
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition( Object object) {
        return POSITION_NONE;
    }
}
