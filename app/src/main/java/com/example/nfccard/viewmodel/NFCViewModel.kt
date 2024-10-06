package com.example.nfccard.viewmodel

import android.content.Context
import com.example.nfccard.nfc.NFCUtil
import com.example.nfccard.repository.UserRepository
import com.example.nfccard.util.EncryptionUtil
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nfccard.model.User
import kotlinx.coroutines.launch

//class NFCViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val userRepository = UserRepository(application)
//
//    suspend fun generateNfcId() {
//        val nfcId = NFCUtil.generateUniqueNfcId()
//        val encryptedId = EncryptionUtil.encrypt(nfcId)
//        userRepository.saveNfcId(encryptedId)
//    }
//
//    suspend fun getNfcId(): String? {
//        val encryptedId = userRepository.getNfcId()
//        return encryptedId?.let { EncryptionUtil.decrypt(it) }
//    }
//
//    fun checkNfcEnabled(): Boolean {
//        return NFCUtil.isNfcEnabled(getApplication())
//    }
//
//    fun enableNfc() {
//        NFCUtil.enableNfc(getApplication())
//    }
//}



class NFCViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    fun generateNfcId() {
        viewModelScope.launch {
            val nfcId = NFCUtil.generateUniqueNfcId()
            val encryptedId = EncryptionUtil.encrypt(nfcId)
            userRepository.saveNfcId(encryptedId)
            // Сохранение NFC ID в объекте пользователя
            val user = userRepository.getUser() ?: return@launch
            user.nfcId = encryptedId
            userRepository.saveUser(user)
        }
    }


    suspend fun getNfcId(): String? {
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
