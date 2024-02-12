package com.elmirov.tinkofftesttask.data.datasource

import com.elmirov.tinkofftesttask.data.local.database.FavoriteFilmsDao
import com.elmirov.tinkofftesttask.util.MockData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalFilmDataSourceImplTest {

    private val dao: FavoriteFilmsDao = mock()
    private val dataSource = LocalFilmDataSourceImpl(dao)

    @Test
    fun `getAll EXPECT list film db model`() = runTest {
        whenever(dao.getAll()) doReturn MockData.listFilmDbModel

        val expected = MockData.listFilmDbModel
        val actual = dataSource.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `add EXPECT insert in db`() = runTest {
        dataSource.add(MockData.filmDbModel)

        verify(dao).add(MockData.filmDbModel)
    }
}