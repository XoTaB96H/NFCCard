package com.example.nfccard.repository

import android.content.Context
import com.example.nfccard.data.DataStoreManager
import com.example.nfccard.model.User

class UserRepository(context: Context) {

    private val dataStore = DataStoreManager(context)

    suspend fun savePinCode(pin: String) {
        dataStore.savePinCode(pin)
    }

    suspend fun getPinCode(): String? {
        return dataStore.getPinCode()
    }

    suspend fun saveUser(user: User) {
        dataStore.saveUser(user)
    }

    suspend fun getUser(): User? {
        return dataStore.getUser()
    }

    suspend fun saveNfcId(nfcId: String) {
        dataStore.saveNfcId(nfcId)
    }

    suspend fun getNfcId(): String? {
        return dataStore.getNfcId()
    }
}
