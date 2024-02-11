package com.elmirov.tinkofftesttask.domain.repository

import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getById(id: Int): FilmFull

    suspend fun getPopular(): Flow<PagingData<FilmFull>>

    suspend fun add(filmFull: FilmFull)

    suspend fun getFavorites(): List<FilmFull>
}