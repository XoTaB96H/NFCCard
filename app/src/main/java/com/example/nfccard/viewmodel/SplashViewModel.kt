package com.example.nfccard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nfccard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    private val _isPinSet = MutableStateFlow<Boolean?>(null)
    val isPinSet: StateFlow<Boolean?> get() = _isPinSet

    init {
        checkPin()
    }

    private fun checkPin() {
        viewModelScope.launch {
            val pin = userRepository.getPinCode()
            _isPinSet.value = !pin.isNullOrEmpty()
        }
    }
}
