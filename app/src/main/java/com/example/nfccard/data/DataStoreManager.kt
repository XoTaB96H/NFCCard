package com.example.nfccard.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.nfccard.data.AppDataStore.dataStore
import com.example.nfccard.model.PassEvent
import com.example.nfccard.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class DataStoreManager private constructor(private val context: Context) {

    private val dataStore = context.dataStore

    // Остальной код

    companion object {
        @Volatile
        private var INSTANCE: DataStoreManager? = null

        fun getInstance(context: Context): DataStoreManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataStoreManager(context.applicationContext).also { INSTANCE = it }
            }
        }

        // Определение ключей
        val PIN_CODE_KEY = stringPreferencesKey("pin_code")
        val NFC_ID_KEY = stringPreferencesKey("nfc_id")
        val USER_KEY = stringPreferencesKey("user")
        val PASS_HISTORY_KEY = stringPreferencesKey("pass_history")
    }



    private val json = Json { ignoreUnknownKeys = true }



    // Сохранение строки
    suspend fun saveString(key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // Получение строки
    fun getStringFlow(key: Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun getString(key: Preferences.Key<String>): String? {
        return context.dataStore.data.map { preferences ->
            preferences[key]
        }.first()
    }

    // Сохранение PIN-кода
    suspend fun savePinCode(pin: String) {
        saveString(PIN_CODE_KEY, pin)
    }

    // Получение PIN-кода
    suspend fun getPinCode(): String? {
        return getString(PIN_CODE_KEY)
    }

    // Сохранение NFC-идентификатора
    suspend fun saveNfcId(nfcId: String) {
        saveString(NFC_ID_KEY, nfcId)
    }

    // Получение NFC-идентификатора
    suspend fun getNfcId(): String? {
        return getString(NFC_ID_KEY)
    }

    // Сохранение пользователя
    suspend fun saveUser(user: User) {
        val userJson = json.encodeToString(user)
        saveString(USER_KEY, userJson)
    }

    // Получение пользователя
    suspend fun getUser(): User? {
        val userJson = getString(USER_KEY)
        return userJson?.let { json.decodeFromString<User>(it) }
    }

    // Добавление события прохода
    suspend fun addPassEvent(event: PassEvent) {
        val currentHistory = getPassHistory().first().toMutableList()
        currentHistory.add(0, event) // Добавляем в начало списка
        val historyJson = json.encodeToString(currentHistory)
        saveString(PASS_HISTORY_KEY, historyJson)
    }

    // Получение истории проходов
    fun getPassHistory(): Flow<List<PassEvent>> {
        return getStringFlow(PASS_HISTORY_KEY).map { historyJson ->
            historyJson?.let {
                json.decodeFromString<List<PassEvent>>(it)
            } ?: emptyList()
        }
    }
}