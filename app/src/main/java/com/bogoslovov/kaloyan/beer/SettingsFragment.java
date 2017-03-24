package com.bogoslovov.kaloyan.beer;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by kaloqn on 3/23/17.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}