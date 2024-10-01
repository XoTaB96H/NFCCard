package com.example.nfccard.viewmodel

import android.content.Context
import com.example.nfccard.nfc.NFCUtil
import com.example.nfccard.repository.UserRepository
import com.example.nfccard.util.EncryptionUtil
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NFCViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    fun generateNfcId() {
        val nfcId = NFCUtil.generateUniqueNfcId()
        val encryptedId = EncryptionUtil.encrypt(nfcId)
        userRepository.saveNfcId(encryptedId)
    }

    fun getNfcId(): String? {
        val encryptedId = userRepository.getNfcId()
        return encryptedId?.let { EncryptionUtil.decrypt(it) }
    }

    fun checkNfcEnabled(): Boolean {
        return NFCUtil.isNfcEnabled(getApplication())
    }

    fun enableNfc() {
        NFCUtil.enableNfc(getApplication())
    }
}