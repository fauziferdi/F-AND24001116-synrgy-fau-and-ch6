package com.example.fauzi_chalange_chapter6.ui.listplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fauzi_chalange_chapter6.domain.repository.PlayerRepository
import com.example.fauzi_chalange_chapter6.domain.model.Player
import kotlinx.coroutines.launch


class ListPlayerFragmentViewModel(
    private val repository: PlayerRepository,
) : ViewModel() {

    private val _clubs : MutableLiveData<List<Player>> = MutableLiveData()
    val clubs : LiveData<List<Player>> = _clubs

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun retriveClubData() {
        // Create and return a list of sample movie data
        viewModelScope.launch {
            try {
                _loading.value = true
                _clubs.value = repository.fetchData()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable.message
            }
        }
    }

}