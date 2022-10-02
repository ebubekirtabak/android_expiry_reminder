package com.geng.expiry_reminder.ui.preferences

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.utils.AppThemeUtils

class PreferencesFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_preferences, rootKey)

        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(requireContext())
        preferenceManager?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        when (p1) {
            "theme_style" -> {
                val theme = p0?.getString("theme_style", "system").toString()
                AppThemeUtils().updateAppTheme(theme)
            }
        }
    }
}