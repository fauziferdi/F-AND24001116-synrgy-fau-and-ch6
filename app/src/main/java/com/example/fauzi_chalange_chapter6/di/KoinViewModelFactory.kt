package com.example.fauzi_chalange_chapter6.di

import com.example.fauzi_chalange_chapter6.ui.detailplayer.DetailPlayerLogicViewModel
import com.example.fauzi_chalange_chapter6.ui.favoriteplayer.FavoritePlayerViewModel
import com.example.fauzi_chalange_chapter6.ui.imageblur.ImageBlurViewModel
import com.example.fauzi_chalange_chapter6.ui.listplayer.ListPlayerFragmentViewModel
import com.example.fauzi_chalange_chapter6.ui.login.LoginViewModel
import com.example.fauzi_chalange_chapter6.ui.navigator.NavigatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModuleFactory = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { DetailPlayerLogicViewModel(playerRepository = get()) }
    viewModel { FavoritePlayerViewModel(playerRepository = get()) }
    viewModel { ListPlayerFragmentViewModel(repository = get()) }
    viewModel { DetailPlayerLogicViewModel(playerRepository = get()) }
    viewModel { NavigatorViewModel(authRepository = get()) }
    viewModel { ImageBlurViewModel(get()) }
}
