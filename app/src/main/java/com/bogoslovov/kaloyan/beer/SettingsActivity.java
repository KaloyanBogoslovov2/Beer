package com.bogoslovov.kaloyan.beer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kaloqn on 3/23/17.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.settings_title);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
