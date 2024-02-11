package com.elmirov.tinkofftesttask.data.datasource

import com.elmirov.tinkofftesttask.data.local.database.FavoriteFilmsDao
import com.elmirov.tinkofftesttask.data.local.model.FilmDbModel
import javax.inject.Inject

interface LocalFilmDataSource {

    suspend fun getAll(): List<FilmDbModel>

    suspend fun add(film: FilmDbModel)
}

class LocalFilmDataSourceImpl @Inject constructor(
    private val dao: FavoriteFilmsDao,
) : LocalFilmDataSource {
    override suspend fun getAll(): List<FilmDbModel> =
        dao.getAll()

    override suspend fun add(film: FilmDbModel) {
        dao.add(film)
    }
}