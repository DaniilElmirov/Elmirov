package com.elmirov.tinkofftesttask.data.datasource

import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskApi
import com.elmirov.tinkofftesttask.util.MockData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteFilmDataSourceImplTest {

    private val api: KinopoiskApi = mock()
    private val dataSource = RemoteFilmDataSourceImpl(api)

    @Test
    fun `get EXPECT film full dto`() = runTest {
        val id = MockData.ID

        whenever(api.getById(id)) doReturn MockData.filmFullDto

        val expected = MockData.filmFullDto
        val actual = dataSource.getById(id)

        assertEquals(expected, actual)
    }
}