package com.geng.expiry_reminder.ui.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.geng.expiry_reminder.R

class PreferencesFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_preferences, rootKey)
    }
}