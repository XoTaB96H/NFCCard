// AppDataStore.kt
package com.example.nfccard.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object AppDataStore {
    private const val DATASTORE_NAME = "app_preferences"

    val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)
}
