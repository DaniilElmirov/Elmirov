package com.elmirov.tinkofftesttask.domain.repository

import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getById(id: Int): Film

    suspend fun getPopular(): Flow<PagingData<Film>>

    suspend fun add(film: Film)

    suspend fun getFavorites(): List<Film>
}