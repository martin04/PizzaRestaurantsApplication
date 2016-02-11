package com.martin.pizzarestaurantsapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.martin.pizzarestaurantsapplication.adapters.CustomFragmentAdapter;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnFragmentInteractionListener, RestaurantMapFragment.OnFragmentMapInteractionListener {

    private static final int REQUEST_CODE = 2;

    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>" + getString(R.string.app_name) + "</font>"));
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {

            Toast.makeText(this, "Datasync changed to:" + data.getBooleanExtra(SettingsActivity.KEY_DATA_SYNC, true), Toast.LENGTH_LONG).show();
            
        }
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
