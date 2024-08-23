package com.example.fauzi_chalange_chapter5.ui.listclub

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


class ListClubFragmentViewModel(
    private val repository: ClubRepository,
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
                    return com.example.fauzi_chalange_chapter5.ui.listclub.ListClubFragmentViewModel(
                        repository = clubRepository,
                    ) as T
                }
            }
    }

    private val _clubs : MutableLiveData<List<Club>> = MutableLiveData()
    val clubs : LiveData<List<Club>> = _clubs

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