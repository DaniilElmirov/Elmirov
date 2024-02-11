package com.elmirov.tinkofftesttask.domain.usecase

import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(
    private val repository: FilmRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<FilmFull>> =
        repository.getPopular()
}