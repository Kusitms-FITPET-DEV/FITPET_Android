package com.example.fitpet.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

private val Context.dataStore by preferencesDataStore(name = "fit_pet_prefs")

@SuppressLint("TimberArgCount")
class FitPetDataStore(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    val accessToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    val refreshToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
            Timber.i("Access Token", token)
        }
    }

    suspend fun saveRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = token
            Timber.i("Refresh Token", token)
        }
    }
}