package com.android.espermobiles.di

import com.android.espermobiles.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build MainViewModel
    viewModel {
        MainViewModel(repository = get())
    }

}