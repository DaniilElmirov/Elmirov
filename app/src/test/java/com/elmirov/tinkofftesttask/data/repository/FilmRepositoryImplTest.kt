package com.elmirov.tinkofftesttask.data.repository

import com.elmirov.tinkofftesttask.data.datasource.LocalFilmDataSource
import com.elmirov.tinkofftesttask.data.datasource.RemoteFilmDataSource
import com.elmirov.tinkofftesttask.util.MockData
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FilmRepositoryImplTest {

    private val remoteDataSource: RemoteFilmDataSource = mock()
    private val localDataSource: LocalFilmDataSource = mock()
    private val dispatcher = StandardTestDispatcher()

    private val repository = FilmRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        dispatcherIo = dispatcher,
    )

    @Test
    fun `get by id EXPECT film full`() = runTest(dispatcher) {
        val id = MockData.ID

        whenever(remoteDataSource.getById(id)) doReturn MockData.filmFullDto

        val expected = MockData.filmFull
        val actual = repository.getById(id)

        assertEquals(expected, actual)
    }

    @Test
    fun `add EXPECT insert in db`() = runTest(dispatcher) {
        val filmPartial = MockData.filmPartial
        val filmDbModel = MockData.filmDbModel

        repository.add(filmPartial)

        verify(localDataSource).add(filmDbModel)
    }

    @Test
    fun `get favorites EXPECT list film partial`() = runTest(dispatcher) {

        whenever(localDataSource.getAll()) doReturn MockData.listFilmDbModel

        val expected = MockData.listFilmPartial
        val actual = repository.getFavorites()

        assertEquals(expected, actual)
    }
}