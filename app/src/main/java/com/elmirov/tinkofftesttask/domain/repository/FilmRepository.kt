package com.elmirov.tinkofftesttask.domain.repository

import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getById(id: Int): FilmFull

    suspend fun getPopular(): Flow<PagingData<FilmPartial>>

    suspend fun add(filmPartial: FilmPartial)

    suspend fun getFavorites(): List<FilmPartial>
}