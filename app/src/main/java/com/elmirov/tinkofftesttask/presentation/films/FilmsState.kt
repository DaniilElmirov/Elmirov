package com.elmirov.tinkofftesttask.presentation.films

import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.presentation.ErrorType

sealed interface FilmsState {

    data object Initial : FilmsState

    data object Loading : FilmsState

    data class Error(
        val type: ErrorType,
    ) : FilmsState

    data class Content(
        val content: List<Film>,
    ) : FilmsState
}