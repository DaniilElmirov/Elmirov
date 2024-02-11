package com.elmirov.tinkofftesttask.presentation.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.usecase.GetPopularFilmsUseCase
import com.elmirov.tinkofftesttask.navigation.router.FilmsRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmsViewModel @Inject constructor(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val router: FilmsRouter,
) : ViewModel() {

    private val _films = MutableStateFlow<PagingData<FilmFull>>(PagingData.empty())
    val films = _films.asStateFlow()

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            getPopularFilmsUseCase().cachedIn(viewModelScope).collect {
                _films.value = it
            }
        }
    }

    fun openInfo(id: Int) {
        router.openFilmInfo(id)
    }
}