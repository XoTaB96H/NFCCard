package com.example.nfccard.viewmodel

import android.content.Context
import com.example.nfccard.nfc.NFCUtil
import com.example.nfccard.repository.UserRepository
import com.example.nfccard.util.EncryptionUtil

class NFCViewModel(
    private val context: Context,
    private val userRepository: UserRepository = UserRepository()
) {

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
        return NFCUtil.isNfcEnabled(context)
    }

    fun enableNfc() {
        NFCUtil.enableNfc(context)
    }
}
