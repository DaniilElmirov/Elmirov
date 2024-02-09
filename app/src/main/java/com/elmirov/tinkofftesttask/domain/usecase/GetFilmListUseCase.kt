package com.elmirov.tinkofftesttask.domain.usecase

import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmListUseCase @Inject constructor(
    private val repository: FilmRepository,
) {
    suspend operator fun invoke(): List<Film> =
        repository.getList()
}