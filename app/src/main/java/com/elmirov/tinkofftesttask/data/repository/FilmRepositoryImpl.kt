package com.elmirov.tinkofftesttask.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.elmirov.tinkofftesttask.data.datasource.RemoteFilmDataSource
import com.elmirov.tinkofftesttask.data.mapper.toEntity
import com.elmirov.tinkofftesttask.di.annotation.DispatcherIo
import com.elmirov.tinkofftesttask.domain.entity.Film
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
    override suspend fun getById(id: Int): Film = withContext(dispatcherIo) {
        remoteDataSource.getById(id).toEntity()
    }

    override suspend fun getPopular(): Flow<PagingData<Film>> = withContext(dispatcherIo) {
        remoteDataSource.getList().map { pagingData ->
            pagingData.map {
                it.toEntity()
            }
        }
    }

    override suspend fun add(film: Film) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<Film> {
        TODO("Not yet implemented")
    }
}