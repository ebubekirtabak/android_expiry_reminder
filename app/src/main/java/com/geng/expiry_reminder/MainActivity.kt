package com.geng.expiry_reminder

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.geng.expiry_reminder.databinding.ActivityMainBinding
import com.geng.expiry_reminder.preferences.SettingsPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var fab2: FloatingActionButton
    lateinit var fab3: FloatingActionButton
    var isFABOpen: Boolean = false
    var backButtonPressed: Boolean = false
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


        var fab = findViewById<View>(R.id.new_action_button) as FloatingActionButton
        fab2 = findViewById<View>(R.id.new_category_button) as FloatingActionButton
        fab3 = findViewById<View>(R.id.new_reminder_button) as FloatingActionButton
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (!isFABOpen) {
                    showFABMenu()
                } else {
                    closeFABMenu()
                }
            }
        })

    }

    private suspend fun handleFirstRun(isFirstRun: Boolean, settingsPreferences: SettingsPreferences) {
        if (!isFirstRun) {
            return
        }

        settingsPreferences.setIsFirstRun(false)
    }


    private fun showFABMenu() {
        isFABOpen = true
        fab2.animate().translationY(resources.getDimension(R.dimen.standard_55))
        fab3.animate().translationY(resources.getDimension(R.dimen.standard_105))
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fab2.animate().translationY(resources.getDimension(R.dimen.fab_button_closed))
        fab3.animate().translationY(resources.getDimension(R.dimen.fab_button_closed))

    }

    private fun showToastMessage(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onBackPressed() {
        if (isFABOpen) {
            closeFABMenu()
        }

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