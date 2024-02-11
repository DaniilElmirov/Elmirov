package com.elmirov.tinkofftesttask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.data.mapper.toEntity
import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskApi
import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskPagingSource
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi,
) : FilmRepository {
    override suspend fun getById(id: Int): Film =
        api.getById(id).toEntity()

    override suspend fun getList(): Flow<PagingData<Film>> = Pager(
        config = PagingConfig(pageSize = KinopoiskApi.MAX_PAGE_SIZE),
        pagingSourceFactory = {
            KinopoiskPagingSource(api)
        }
    ).flow
}