package com.example.fyp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.example.fyp.Entity.GlobalVariable;
import com.example.fyp.Entity.Player;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private static String TAG = "SETTINGS ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        final GlobalVariable Instance = GlobalVariable.getInstance();
        ArrayList<Player> Players = Instance.getPlayers();
        if(Players.isEmpty()){
            Log.d(TAG, "Players empty");
        }
        else{
            Log.d(TAG, "Players not empty");
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}