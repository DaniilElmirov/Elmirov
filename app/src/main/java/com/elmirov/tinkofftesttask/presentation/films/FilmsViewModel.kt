package com.elmirov.tinkofftesttask.presentation.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmirov.tinkofftesttask.domain.usecase.GetFilmsUseCase
import com.elmirov.tinkofftesttask.navigation.router.FilmsRouter
import com.elmirov.tinkofftesttask.presentation.ErrorType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmsViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val router: FilmsRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<FilmsState>(FilmsState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = FilmsState.Error(ErrorType.INTERNET)
    }

    init {
        getFilms()
    }

    fun getFilms() {
        viewModelScope.launch(handler) {
            _state.value = FilmsState.Loading

            val films = getFilmsUseCase()
            _state.value = FilmsState.Content(films)
        }
    }

    fun openInfo(id: Int) {
        router.openFilmInfo(id)
    }
}