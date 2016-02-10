package com.martin.pizzarestaurantsapplication;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.martin.pizzarestaurantsapplication.adapters.CustomFragmentAdapter;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnFragmentInteractionListener, RestaurantMapFragment.OnFragmentMapInteractionListener {

    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = (ViewPager) findViewById(R.id.viewpager);
        setUpViewPager(pager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabTextColors(ContextCompat.getColor(this, R.color.colorPrimaryLight), ContextCompat.getColor(this, R.color.colorAccent));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                tabs.setTabTextColors(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryLight), ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        CustomFragmentAdapter adapter = new CustomFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new RestaurantMapFragment(), "Map");
        adapter.addFragment(new RestaurantListFragment(), "List");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentMapInteraction(Uri uri) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
