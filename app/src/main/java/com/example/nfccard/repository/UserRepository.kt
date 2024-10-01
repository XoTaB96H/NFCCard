package com.example.nfccard.repository

import android.content.Context
import com.example.nfccard.data.DataStoreManager
import com.example.nfccard.model.User


class UserRepository(context: Context) {

    private val dataStoreManager = DataStoreManager.getInstance(context)

    suspend fun savePinCode(pin: String) {
        dataStoreManager.savePinCode(pin)
    }

    suspend fun getPinCode(): String? {
        return dataStoreManager.getPinCode()
    }

    suspend fun saveUser(user: User) {
        dataStoreManager.saveUser(user)
    }

    suspend fun getUser(): User? {
        return dataStoreManager.getUser()
    }

    suspend fun saveNfcId(nfcId: String) {
        dataStoreManager.saveNfcId(nfcId)
    }

    suspend fun getNfcId(): String? {
        return dataStoreManager.getNfcId()
    }
}
