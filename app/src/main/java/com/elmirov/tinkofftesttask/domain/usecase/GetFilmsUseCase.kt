package com.elmirov.tinkofftesttask.domain.usecase

import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val repository: FilmRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<Film>> =
        repository.getList()
}