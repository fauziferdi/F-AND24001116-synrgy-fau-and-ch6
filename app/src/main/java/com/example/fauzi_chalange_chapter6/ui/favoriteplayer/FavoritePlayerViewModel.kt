package com.example.fauzi_chalange_chapter6.ui.favoriteplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fauzi_chalange_chapter6.domain.repository.PlayerRepository
import com.example.fauzi_chalange_chapter6.domain.model.Player
import kotlinx.coroutines.launch


class FavoritePlayerViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val _player = MutableLiveData<List<Player>>()
    val clubs: LiveData<List<Player>> = _player

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getClubFromLocal() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _player.value = playerRepository.loadAllClub()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }
}