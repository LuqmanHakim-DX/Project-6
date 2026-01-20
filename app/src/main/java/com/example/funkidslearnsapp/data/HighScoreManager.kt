package com.example.funkidslearnsapp.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("high_scores")

class HighScoreManager(private val context: Context) {

    companion object {
        val COLOR_HIGH = intPreferencesKey("color_high")
        val LETTER_HIGH = intPreferencesKey("letter_high")
        val NUMBER_HIGH = intPreferencesKey("number_high")
    }

    fun getHighScore(key: Preferences.Key<Int>): Flow<Int> =
        context.dataStore.data.map { it[key] ?: 0 }

    suspend fun saveHighScore(key: Preferences.Key<Int>, score: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[key] ?: 0
            if (score > current) {
                prefs[key] = score
            }
        }
    }
}
