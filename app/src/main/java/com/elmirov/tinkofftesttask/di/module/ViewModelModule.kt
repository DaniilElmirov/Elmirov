package com.elmirov.tinkofftesttask.di.module

import androidx.lifecycle.ViewModel
import com.elmirov.tinkofftesttask.di.annotation.ViewModelKey
import com.elmirov.tinkofftesttask.presentation.films.FilmsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilmsViewModel::class)
    fun bindFilmsViewModel(viewModel: FilmsViewModel): ViewModel
}