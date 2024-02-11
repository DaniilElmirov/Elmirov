package com.elmirov.tinkofftesttask.domain.usecase

import com.elmirov.tinkofftesttask.domain.entity.FilmPartial
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend operator fun invoke(): List<FilmPartial> =
        repository.getFavorites()
}