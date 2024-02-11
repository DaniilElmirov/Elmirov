package com.elmirov.tinkofftesttask.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.elmirov.tinkofftesttask.data.datasource.RemoteFilmDataSource
import com.elmirov.tinkofftesttask.data.mapper.toEntity
import com.elmirov.tinkofftesttask.di.annotation.DispatcherIo
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteFilmDataSource,
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher,
) : FilmRepository {
    override suspend fun getById(id: Int): FilmFull = withContext(dispatcherIo) {
        remoteDataSource.getById(id).toEntity()
    }

    override suspend fun getPopular(): Flow<PagingData<FilmFull>> = withContext(dispatcherIo) {
        remoteDataSource.getList().map { pagingData ->
            pagingData.map {
                it.toEntity()
            }
        }
    }

    override suspend fun add(filmFull: FilmFull) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<FilmFull> {
        TODO("Not yet implemented")
    }
}