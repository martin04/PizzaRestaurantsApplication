package com.martin.pizzarestaurantsapplication;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestaurantListFragment fragment = new RestaurantListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, RestaurantListFragment.TAG).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
