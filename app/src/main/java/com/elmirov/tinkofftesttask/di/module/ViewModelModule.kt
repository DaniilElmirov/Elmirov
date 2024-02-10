package com.elmirov.tinkofftesttask.di.module

import androidx.lifecycle.ViewModel
import com.elmirov.tinkofftesttask.di.annotation.ViewModelKey
import com.elmirov.tinkofftesttask.presentation.activity.MainActivityViewModel
import com.elmirov.tinkofftesttask.presentation.films.FilmsViewModel
import com.elmirov.tinkofftesttask.presentation.info.FilmInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilmsViewModel::class)
    fun bindFilmsViewModel(viewModel: FilmsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilmInfoViewModel::class)
    fun bindFilmInfoViewModel(viewModel: FilmInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}