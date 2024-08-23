package com.example.fauzi_chalange_chapter5.ui.detailclub

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fauzi_chalange_chapter5.data.datasource.local.ClubLocalDataSourceImpl
import com.example.fauzi_chalange_chapter5.data.datasource.local.room.RoomDatabase
import com.example.fauzi_chalange_chapter5.data.datasource.remote.ClubRemoteDataSourceImpl
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.provideClubService
import com.example.fauzi_chalange_chapter5.domain.ClubRepository
import com.example.fauzi_chalange_chapter5.data.repository.ClubRepositoryImpl
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club
import kotlinx.coroutines.launch


class DetailClubLogic(
    private val clubRepository: ClubRepository,
) : ViewModel() {
    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val roomDatabase = Room.databaseBuilder(
                        context = context,
                        name = RoomDatabase.NAME,
                        klass = RoomDatabase::class.java,
                    ).build()
                    val clubRepository: ClubRepository = ClubRepositoryImpl(
                        remoteDataSource = ClubRemoteDataSourceImpl(
                            apiClubService = provideClubService(context)
                        ),
                        localDataSource = ClubLocalDataSourceImpl(
                            clubDao = roomDatabase.clubDao()
                        ),
                    )
                    return DetailClubLogic(
                        clubRepository = clubRepository,
                    )as T
                }
            }
    }

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
                val club = Club(
                    nama = nama,
                    image = image,
                    id = if (id == -1) {
                        null
                    } else {
                        id
                    },
                )
                clubRepository.saveFavorite(club)
                _insertClub.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }



    private val _deleteClub = MutableLiveData<Boolean>()
    val deleteClub: LiveData<Boolean> = _deleteClub

    fun deleteClubFromFavorite(club: Club){
        viewModelScope.launch {
            try {
                clubRepository.deleteClub(club)
                _deleteClub.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    private val _clubLocal = MutableLiveData<Club?>()
    val clubLocal: LiveData<Club?> = _clubLocal
    fun loadClubFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                _clubLocal.value = clubRepository.loadClubById(id)
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }



}

