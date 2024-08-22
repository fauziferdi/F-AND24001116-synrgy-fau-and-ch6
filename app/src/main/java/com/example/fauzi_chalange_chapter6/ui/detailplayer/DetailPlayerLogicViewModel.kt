package com.example.fauzi_chalange_chapter6.ui.detailplayer


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fauzi_chalange_chapter6.domain.repository.PlayerRepository
import com.example.fauzi_chalange_chapter6.domain.model.Player
import kotlinx.coroutines.launch


class DetailPlayerLogicViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    private val  _error =MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    var title: String? = null

    fun getUrlInternet(title: String): String {
        return "https://www.google.com/search?q=$title"
    }

    private val _insertClub = MutableLiveData<Boolean>()
    val insertClub: LiveData<Boolean> = _insertClub

    fun saveClubToFavorite(
        nama: String,
        image: String,
        id: Int = -1,
    ) {
        viewModelScope.launch {
            try {
                val player = Player(
                    nama = nama,
                    image = image,
                    id = if (id == -1) {
                        null
                    } else {
                        id
                    },
                )
                playerRepository.saveFavorite(player)
                _insertClub.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }



    private val _deleteClub = MutableLiveData<Boolean>()
    val deleteClub: LiveData<Boolean> = _deleteClub

    fun deleteClubFromFavorite(player: Player){
        viewModelScope.launch {
            try {
                playerRepository.deleteClub(player)
                _deleteClub.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    private val _playerLocal = MutableLiveData<Player?>()
    val playerLocal: LiveData<Player?> = _playerLocal
    fun loadClubFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                _playerLocal.value = playerRepository.loadClubById(id)
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }



}

