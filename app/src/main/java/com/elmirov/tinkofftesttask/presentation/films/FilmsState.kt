package com.elmirov.tinkofftesttask.presentation.films

import com.elmirov.tinkofftesttask.domain.entity.Film

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

enum class ErrorType {
    INTERNET
}

