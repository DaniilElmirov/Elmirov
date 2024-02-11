package com.elmirov.tinkofftesttask.presentation.info

import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.presentation.ErrorType

sealed interface FilmInfoState {

    data object Initial : FilmInfoState

    data object Loading : FilmInfoState

    data class Error(
        val type: ErrorType,
    ) : FilmInfoState

    data class Content(
        val content: FilmFull,
    ) : FilmInfoState
}