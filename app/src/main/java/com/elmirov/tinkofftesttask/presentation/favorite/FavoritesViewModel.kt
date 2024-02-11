package com.elmirov.tinkofftesttask.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmirov.tinkofftesttask.domain.usecase.GetFavoriteFilmsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = FavoritesState.Error(ErrorType.DATABASE)
    }

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch(handler) {
            _state.value = FavoritesState.Loading

            val films = getFavoriteFilmsUseCase()
            _state.value = FavoritesState.Content(films)
        }
    }
}