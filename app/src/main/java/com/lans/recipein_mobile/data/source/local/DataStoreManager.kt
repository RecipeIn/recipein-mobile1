package com.lans.recipein_mobile.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val context: Context) {
    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
        private const val DATASTORE = "storeapp"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.remove(EMAIL)
        }
    }

    val email: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
}