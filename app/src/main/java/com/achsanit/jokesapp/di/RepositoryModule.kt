package com.achsanit.jokesapp.di

import com.achsanit.jokesapp.data.Repository
import com.achsanit.jokesapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get()) } // instance repository

    viewModel { HomeViewModel(get()) }
    single { HomeViewModel(get()) }
}