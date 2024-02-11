package com.elmirov.tinkofftesttask.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmirov.tinkofftesttask.domain.usecase.GetFilmByIdUseCase
import com.elmirov.tinkofftesttask.navigation.router.FilmInfoRouter
import com.elmirov.tinkofftesttask.presentation.ErrorType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmInfoViewModel @Inject constructor(
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val router: FilmInfoRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<FilmInfoState>(FilmInfoState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = FilmInfoState.Error(ErrorType.INTERNET)
    }

    fun loadFilm(id: Int) {
        viewModelScope.launch(handler) {
            _state.value = FilmInfoState.Loading

            val film = getFilmByIdUseCase(id)
            _state.value = FilmInfoState.Content(film)
        }
    }

    fun backToFilms() {
        router.backToFilms()
    }
}