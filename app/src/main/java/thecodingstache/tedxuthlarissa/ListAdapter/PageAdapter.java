package thecodingstache.tedxuthlarissa.ListAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CoreTeamFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.CurrationFragment;
import thecodingstache.tedxuthlarissa.Fragment.CoreTeam.DirectorsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PageAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new CurrationFragment();
            case 2:
                return new DirectorsFragment();
            case 3:
                return new CoreTeamFragment();
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
