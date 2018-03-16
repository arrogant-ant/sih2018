package inc.iris.sih2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Sud on 3/16/18.
 */

public class BookingsPagerAdapter extends FragmentStatePagerAdapter {

    private int noOfTabs;
    private Fragment tabs[];

    public BookingsPagerAdapter(FragmentManager fm,int noOfTabs,Fragment tabs[]) {
        super(fm);
        this.noOfTabs=noOfTabs;
        this.tabs=tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
