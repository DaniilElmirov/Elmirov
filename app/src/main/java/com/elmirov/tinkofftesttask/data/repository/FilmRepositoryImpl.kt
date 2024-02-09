package com.elmirov.tinkofftesttask.data.repository

import com.elmirov.tinkofftesttask.data.mapper.toEntity
import com.elmirov.tinkofftesttask.data.network.api.KinopoiskApi
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi,
) : FilmRepository {
    override suspend fun getById(id: Int): Film =
        api.getById(id).toEntity()

    override suspend fun getList(): List<Film> =
        api.getFilms().toEntity()
}