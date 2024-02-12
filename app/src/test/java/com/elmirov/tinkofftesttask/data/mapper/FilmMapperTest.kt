package com.elmirov.tinkofftesttask.data.mapper

import com.elmirov.tinkofftesttask.util.MockData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FilmMapperTest {

    @Test
    fun `film full dto to entity EXPECT film full`() {
        val filmFullDto = MockData.filmFullDto

        val expected = MockData.filmFull
        val actual = filmFullDto.toEntity()

        assertEquals(expected, actual)
    }

    @Test
    fun `film partial dto to entity EXPECT film partial`() {
        val filmPartialDto = MockData.filmPartialDto

        val expected = MockData.filmPartial
        val actual = filmPartialDto.toEntity()

        assertEquals(expected, actual)
    }

    @Test
    fun `film db model to entity EXPECT film partial`() {
        val filmDbModel = MockData.filmDbModel

        val expected = MockData.filmPartial
        val actual = filmDbModel.toEntity()

        assertEquals(expected, actual)
    }

    @Test
    fun `film partial to db model EXPECT film db model`() {
        val filmPartial = MockData.filmPartial

        val expected = MockData.filmDbModel
        val actual = filmPartial.toDbModel()

        assertEquals(expected, actual)
    }
}