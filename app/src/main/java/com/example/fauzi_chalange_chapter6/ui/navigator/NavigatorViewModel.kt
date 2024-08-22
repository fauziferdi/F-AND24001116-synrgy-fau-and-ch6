package com.example.fauzi_chalange_chapter6.ui.navigator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fauzi_chalange_chapter6.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class NavigatorViewModel(private val authRepository: AuthRepository) : ViewModel() {


    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkLogin() {
        viewModelScope.launch {
            try {
                _isLoggedIn.value = !authRepository.loadToken().isNullOrEmpty()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }
}