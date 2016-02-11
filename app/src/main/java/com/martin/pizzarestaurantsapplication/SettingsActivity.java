package com.martin.pizzarestaurantsapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by Martin on 2/10/2016.
 */
public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    public static final String KEY_DATA_SYNC = "KEY_DATA_SYNC";

    private Toolbar toolbar;
    private ActionBar actionbar;
    private Switch dataSyncSwitch;
    private SharedPreferences sharedPreferencesCompat;

    private boolean dataSync; //FALSE-no data syn , TRUE - data sync


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionbar.setTitle(Html.fromHtml("<font color='#ffffff'>" + getString(R.string.action_settings) + "</font>"));
        }

        dataSyncSwitch = (Switch) findViewById(R.id.dataSyncSwitch);
        dataSyncSwitch.setOnCheckedChangeListener(this);

       readSettings();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            settingsChangedSuccessfully();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        saveSettings(isChecked);
    }

    /**
     * Function that will save the users settings
     *
     * @param dataSyncing
     */
    private void saveSettings(boolean dataSyncing) {
        sharedPreferencesCompat = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferencesCompat.edit();
        editor.putBoolean(KEY_DATA_SYNC, dataSyncing);
        editor.apply();
    }

    /**
     * Function that will read users settings
     */
    private void readSettings() {
        sharedPreferencesCompat = PreferenceManager.getDefaultSharedPreferences(this);
        dataSync = sharedPreferencesCompat.getBoolean(KEY_DATA_SYNC, false);
        dataSyncSwitch.setChecked(dataSync);
    }

    private void settingsChangedSuccessfully(){
        Intent data = new Intent();
        data.putExtra(KEY_DATA_SYNC, dataSyncSwitch.isChecked());
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        settingsChangedSuccessfully();
    }
}
