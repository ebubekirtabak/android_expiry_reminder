package com.geng.expiry_reminder

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.geng.expiry_reminder.databinding.ActivityMainBinding
import com.geng.expiry_reminder.preferences.SettingsPreferences
import com.geng.expiry_reminder.utils.AppThemeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var backButtonPressed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(AppThemeUtils().getAppThemeStyle(this@MainActivity));
        setAppTheme()
        var isFirstRun: Boolean = false
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_upcoming, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        GlobalScope.launch {
            val settingsPreferences = SettingsPreferences(this@MainActivity)
            settingsPreferences.getIsFirstRun.catch { e ->
                e.printStackTrace()
            }.collect {
                isFirstRun = it
                handleFirstRun(isFirstRun, settingsPreferences)
            }
        }
    }

    private fun setAppTheme() {
        val defaultPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val theme = defaultPref.getString("theme_style", "system").toString();
        AppThemeUtils().updateAppTheme(theme)
    }

    private suspend fun handleFirstRun(isFirstRun: Boolean, settingsPreferences: SettingsPreferences) {
        if (!isFirstRun) {
            // @Todo first run task
            return
        }

        settingsPreferences.setIsFirstRun(false)
    }

    private fun showToastMessage(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onBackPressed() {
/*        if (isFABOpen) {
            closeFABMenu()
        }*/

        if (!backButtonPressed) {
            backButtonPressed = true
            showToastMessage(resources.getString(R.string.press_back_again_to_exit))
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    backButtonPressed = false
                },
                2000
            );
        } else {
            super.onBackPressed()
        }
    }
}