package com.elmirov.tinkofftesttask.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskApi
import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskPagingSource
import com.elmirov.tinkofftesttask.data.remote.model.FilmFullDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmPartialDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RemoteFilmDataSource {

    suspend fun getById(id: Int): FilmFullDto

    suspend fun getList(): Flow<PagingData<FilmPartialDto>>
}

class RemoteFilmDataSourceImpl @Inject constructor(
    private val api: KinopoiskApi,
) : RemoteFilmDataSource {
    override suspend fun getById(id: Int): FilmFullDto =
        api.getById(id)

    override suspend fun getList(): Flow<PagingData<FilmPartialDto>> = Pager(
        config = PagingConfig(pageSize = KinopoiskApi.MAX_PAGE_SIZE),
        pagingSourceFactory = {
            KinopoiskPagingSource(api)
        }
    ).flow
}