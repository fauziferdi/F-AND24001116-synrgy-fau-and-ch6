package com.example.fauzi_chalange_chapter5.ui.login

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
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model.res.ReqresErrorResponse
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.provideReqresService
import com.example.fauzi_chalange_chapter5.domain.AuthRepository
import com.example.fauzi_chalange_chapter5.data.repository.AuthRepositoryImpl
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (
    private val authRepository: AuthRepository,
) : ViewModel() {

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
                        )
                    )
                    return LoginViewModel(authRepository = authRepository) as T
                }
            }
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val logtoken = authRepository.login(username, password)
                authRepository.saveToken(logtoken)
                _loading.value = false
                _success.value = true
            } catch (throwable: Throwable) {
                _loading.value = false
                if (throwable is HttpException) {
                    val json = throwable.response()?.errorBody()?.string()
                    val error = Gson().fromJson(json, ReqresErrorResponse::class.java)
                    _error.value = error.error
                } else {
                    _error.value = throwable.message
                }
            }
        }
    }


}