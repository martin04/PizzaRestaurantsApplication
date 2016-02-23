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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.martin.pizzarestaurantsapplication.adapters.CustomFragmentAdapter;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnFragmentInteractionListener,
        RestaurantMapFragment.OnFragmentMapInteractionListener, View.OnClickListener {

    private static final int REQUEST_CODE = 2;

    private Toolbar toolbar;
    private ImageView imgMap;
    private ImageView imgList;
    private ActionBar actionBar;
    private Toolbar footer;

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

        footer = (Toolbar) findViewById(R.id.footerView);
        imgMap = (ImageView) footer.findViewById(R.id.btnMap);
        imgList = (ImageView) footer.findViewById(R.id.btnList);
        imgMap.setOnClickListener(this);
        imgList.setOnClickListener(this);

        RestaurantMapFragment mapFragment = new RestaurantMapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment, RestaurantMapFragment.TAG).commit();
        imgMap.setImageResource(R.drawable.ic_place_orange_24dp);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
           case R.id.btnMap:
               imgMap.setImageResource(R.drawable.ic_place_orange_24dp);
               imgList.setImageResource(R.drawable.ic_view_list_white_24dp);
                RestaurantMapFragment mapFragment = new RestaurantMapFragment();
               getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment, RestaurantMapFragment.TAG).commit();
            break;
           case R.id.btnList:
               imgMap.setImageResource(R.drawable.ic_place_white_24dp);
               imgList.setImageResource(R.drawable.ic_view_list_orange_24dp);
               RestaurantListFragment listFragment = new RestaurantListFragment();
               getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment, RestaurantListFragment.TAG).commit();
            break;
        }
    }
}
