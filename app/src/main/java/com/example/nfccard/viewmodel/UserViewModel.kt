package com.example.nfccard.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nfccard.model.User
import com.example.nfccard.repository.UserRepository
import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


//class UserViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val userRepository = UserRepository(application)
//
//    suspend fun setUserPhoto(uri: String) {
//        val user = userRepository.getUser() ?: User()
//        user.photoUri = uri
//        userRepository.saveUser(user)
//    }
//
//    suspend fun setUserName(name: String, surname: String) {
//        val user = userRepository.getUser() ?: User()
//        user.name = name
//        user.surname = surname
//        userRepository.saveUser(user)
//    }
//}



class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    fun setUserPhoto(uri: String) {
        viewModelScope.launch {
            val user = userRepository.getUser() ?: User()
            user.photoUri = uri
            userRepository.saveUser(user)
        }
    }

    fun setUserName(name: String, surname: String) {
        viewModelScope.launch {
            val user = userRepository.getUser() ?: User()
            user.name = name
            user.surname = surname
            userRepository.saveUser(user)
        }
    }
}
