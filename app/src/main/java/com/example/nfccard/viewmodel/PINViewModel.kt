package com.example.nfccard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nfccard.repository.UserRepository

class PINViewModel(private val userRepository: UserRepository = UserRepository()) : ViewModel() {

    fun setPinCode(pin: String) {
        userRepository.savePinCode(pin)
    }

    fun verifyPinCode(pin: String): Boolean {
        val savedPin = userRepository.getPinCode()
        return savedPin == pin
    }
}
