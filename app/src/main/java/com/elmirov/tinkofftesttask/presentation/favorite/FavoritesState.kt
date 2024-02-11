package com.elmirov.tinkofftesttask.presentation.favorite

import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

sealed interface FavoritesState {
    data object Initial : FavoritesState

    data object Loading : FavoritesState

    data class Error(
        val type: ErrorType,
    ) : FavoritesState

    data class Content(
        val content: List<FilmPartial>,
    ) : FavoritesState
}

enum class ErrorType {
    DATABASE
}