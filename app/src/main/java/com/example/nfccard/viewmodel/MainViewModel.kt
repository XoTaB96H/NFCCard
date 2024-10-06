package com.example.nfccard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nfccard.model.PassEvent
import com.example.nfccard.model.User
import com.example.nfccard.repository.PassHistoryRepository
import com.example.nfccard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    private val passHistoryRepository = PassHistoryRepository(application)
    private val nfcViewModel = NFCViewModel(application)

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    val passHistory = passHistoryRepository.getPassHistoryFlow()

    init {
        loadUserData()
    }

    fun loadUserData() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }


    fun isNfcEnabled(): Boolean {
        return nfcViewModel.checkNfcEnabled()
    }

    fun enableNfc() {
        nfcViewModel.enableNfc()
    }
}
