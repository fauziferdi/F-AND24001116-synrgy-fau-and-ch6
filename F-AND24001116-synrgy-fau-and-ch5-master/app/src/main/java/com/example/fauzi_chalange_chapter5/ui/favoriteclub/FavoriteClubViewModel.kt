package com.example.fauzi_chalange_chapter5.ui.favoriteclub
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


class FavoriteClubViewModel(
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
                    return FavoriteClubViewModel(
                        clubRepository = clubRepository,
                    )as T
                }
            }
    }

    private val _club = MutableLiveData<List<Club>>()
    val clubs: LiveData<List<Club>> = _club

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getClubFromLocal() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _club.value = clubRepository.loadAllClub()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }
}