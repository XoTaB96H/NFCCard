package com.example.nfccard.repository

import android.content.Context
import com.example.nfccard.data.DataStoreManager
import com.example.nfccard.model.User


class UserRepository(private val dataStore: DataStoreManager = DataStoreManager()) {

    fun savePinCode(pin: String) {
        dataStore.saveString("pin_code", pin)
    }

    fun getPinCode(): String? {
        return dataStore.getString("pin_code")
    }

    fun saveUser(user: User) {
        dataStore.saveUser(user)
    }

    fun getUser(): User? {
        return dataStore.getUser()
    }

    fun saveNfcId(nfcId: String) {
        dataStore.saveString("nfc_id", nfcId)
    }

    fun getNfcId(): String? {
        return dataStore.getString("nfc_id")
    }
}
