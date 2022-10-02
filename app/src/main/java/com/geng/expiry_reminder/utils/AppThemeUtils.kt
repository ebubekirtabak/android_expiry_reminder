package com.geng.expiry_reminder.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.geng.expiry_reminder.R

class AppThemeUtils {
    fun updateAppTheme(theme: String) {
        when(theme) {
            "Light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "Dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "System" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    fun getAppThemeStyle(context: Context): Int {
        val defaultPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val theme = defaultPref.getString("theme_color", "General").toString()
        var themeResourceId: Int = R.style.Theme_Expiryreminder
        when (theme) {
            "Space Cadet" -> themeResourceId = R.style.Theme_SpaceCadet
            "United Nations Blue" -> themeResourceId = R.style.Theme_UnitedNationsBlue
            "Red Light" -> themeResourceId = R.style.Theme_RedLight
        }
        return themeResourceId
    }
}