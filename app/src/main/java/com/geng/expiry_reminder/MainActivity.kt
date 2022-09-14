package com.geng.expiry_reminder

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.geng.expiry_reminder.database.AppDatabase

import com.geng.expiry_reminder.databinding.ActivityMainBinding
import com.geng.expiry_reminder.models.category.Category
import com.geng.expiry_reminder.preferences.SettingsPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
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

        val categoryList = arrayListOf<Category>(
            Category(0,"Fridge", R.drawable.ic_baseline_food_bank_24, 1, R.color.purple_500),
            Category(0,"Freezer", R.drawable.ic_baseline_kitchen_24, 2, R.color.blue),
            Category(0,"Pantry", R.drawable.ic_cupboard_black_24, 3, R.color.gray),
            Category(0,"Documents", R.drawable.ic_baseline_insert_drive_file_24, 4, R.color.deep_jungle_green),
            Category(0,"Payments", R.drawable.ic_baseline_shopping_cart_24, 5, R.color.purple_500) ,
            Category(0,"Medicine", R.drawable.ic_baseline_medical_services_24, 6, R.color.red)
        )
        val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
        database.CategoryDao().insertAll(categoryList)
        val category1 = database.CategoryDao().findByName("Doc")
        Log.d("Category", category1.name);
        settingsPreferences.setIsFirstRun(false)
    }
}