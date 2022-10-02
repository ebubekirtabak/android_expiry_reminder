package com.geng.expiry_reminder.preferences
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SettingsPreferences(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")
        private val isFirstRun = booleanPreferencesKey("is_first_run")
    }

    val getIsFirstRun: Flow<Boolean>
        get() = context.dataStore.data.map {
            it[isFirstRun] ?: true
        }

    suspend fun setIsFirstRun(value: Boolean) {
        context.dataStore.edit { it[isFirstRun] = value }
    }

}