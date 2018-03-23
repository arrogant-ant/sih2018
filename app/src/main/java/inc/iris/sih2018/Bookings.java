package inc.iris.sih2018;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bookings extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BookingsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        setViewPager();
    }

    private void setViewPager() {
        tabLayout=findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Past"));
        tabLayout.addTab(tabLayout.newTab().setText("Current"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Fragment tabs[]=new Fragment[]{new PastBookingTab(),new CurrentBookingTab(),new UpComingBookingTab()};

        viewPager=findViewById(R.id.view_pager);
        pagerAdapter=new BookingsPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),tabs);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
