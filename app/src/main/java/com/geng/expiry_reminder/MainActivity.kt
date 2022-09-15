package com.geng.expiry_reminder

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.geng.expiry_reminder.databinding.ActivityMainBinding
import com.geng.expiry_reminder.preferences.SettingsPreferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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

    private suspend fun handleFirstRun(isFirstRun: Boolean, settingsPreferences: SettingsPreferences) {
        if (!isFirstRun) {
            return
        }

        settingsPreferences.setIsFirstRun(false)
    }
}