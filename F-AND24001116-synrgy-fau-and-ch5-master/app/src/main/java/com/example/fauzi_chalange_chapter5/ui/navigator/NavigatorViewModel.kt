package com.example.fauzi_chalange_chapter5.ui.navigator

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fauzi_chalange_chapter5.data.datasource.local.AuthLocalDataSourceImpl
import com.example.fauzi_chalange_chapter5.data.datasource.local.dataStore
import com.example.fauzi_chalange_chapter5.data.datasource.remote.AuthRemoteDataSourceImpl
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.provideReqresService
import com.example.fauzi_chalange_chapter5.domain.AuthRepository
import com.example.fauzi_chalange_chapter5.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.launch

class NavigatorViewModel(private val authRepository: AuthRepository)
    : ViewModel() {
        companion object{
            fun provideFactory(
                owner: SavedStateRegistryOwner,
                context: Context,
            ) : AbstractSavedStateViewModelFactory =
                object : AbstractSavedStateViewModelFactory(owner, null) {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(
                        key: String,
                        modelClass: Class<T>,
                        handle: SavedStateHandle,
                    ): T {
                        val authRepository: AuthRepository = AuthRepositoryImpl(
                            authLocalDataSource = AuthLocalDataSourceImpl(
                                dataStore = context.dataStore,
                            ),
                            authRemoteDataSource = AuthRemoteDataSourceImpl(
                                reqresService = provideReqresService(context)
                            ),
                        )
                        return NavigatorViewModel(authRepository=authRepository) as T
                    }
                }
        }

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