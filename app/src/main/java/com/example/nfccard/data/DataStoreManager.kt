package com.example.nfccard.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.nfccard.model.PassEvent
import com.example.nfccard.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "app_preferences")

    suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String): String? {
        // Реализация получения строки из DataStore
        return null
    }

    suspend fun saveUser(user: User) {
        // Сериализовать и сохранить объект пользователя
    }

    fun getUser(): User? {
        // Получить и десериализовать объект пользователя
        return null
    }

    suspend fun addPassEvent(event: PassEvent) {
        // Добавить событие в список истории
    }

    fun getPassHistory(): Flow<List<PassEvent>> {
        // Вернуть поток с историей проходов
        return context.dataStore.data.map { preferences ->
            // Преобразовать сохраненные данные в список PassEvent
            emptyList()
        }
    }
}
