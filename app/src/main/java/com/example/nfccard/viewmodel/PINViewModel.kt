package com.example.nfccard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nfccard.repository.UserRepository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class PINViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    fun setPinCode(pin: String) {
        viewModelScope.launch {
            userRepository.savePinCode(pin)
        }
    }

    suspend fun verifyPinCode(pin: String): Boolean {
        val savedPin = userRepository.getPinCode()
        return savedPin == pin
    }

    suspend fun isPinSet(): Boolean {
        val pin = userRepository.getPinCode()
        return !pin.isNullOrEmpty()
    }
}

