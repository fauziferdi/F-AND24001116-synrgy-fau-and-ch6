package com.example.fauzi_chalange_chapter6.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.fauzi_chalange_chapter6.data.datasource.AuthLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthLocalDataSourceImpl (
    private val dataStore: DataStore<Preferences>
) : AuthLocalDataSource {

    companion object{
        const val KEY_LOG = "logtoken"
        private val DATASTORE_KEY_LOG = stringPreferencesKey(KEY_LOG)
    }

    override suspend fun saveToken(logtoken: String) {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_LOG] = logtoken
        }
    }

    override suspend fun loadToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[DATASTORE_KEY_LOG]
        }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_LOG] = ""
        }
    }



}