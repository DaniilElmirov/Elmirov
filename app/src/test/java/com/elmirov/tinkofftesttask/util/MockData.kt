package com.elmirov.tinkofftesttask.util

import com.elmirov.tinkofftesttask.data.local.model.FilmDbModel
import com.elmirov.tinkofftesttask.data.remote.model.CountryDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmFullDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmPartialDto
import com.elmirov.tinkofftesttask.data.remote.model.GenreDto
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

object MockData {

    const val ID = 0

    private const val genre = "genre"
    private const val country = "country"

    private val genreDto = GenreDto(genre)
    private val countryDto = CountryDto(country)

    val filmDbModel = FilmDbModel(
        id = 0,
        name = "Name",
        posterPreviewUrl = "url",
        year = 2024,
        genre = genre
    )

    val listFilmDbModel = listOf(filmDbModel, filmDbModel, filmDbModel)

    val filmFullDto = FilmFullDto(
        id = ID,
        name = "Name",
        posterUrl = "url",
        year = 2024,
        description = "description",
        genresDto = listOf(genreDto, genreDto, genreDto),
        countriesDto = listOf(countryDto, countryDto, countryDto)
    )

    val filmFull = FilmFull(
        id = ID,
        name = "Name",
        posterUrl = "url",
        year = 2024,
        description = "description",
        genres = listOf(genre, genre, genre),
        countries = listOf(country, country, country)
    )

    val filmPartialDto = FilmPartialDto(
        id = ID,
        name = "Name",
        posterPreviewUrl = "url",
        year = 2024,
        genresDto = listOf(genreDto, genreDto, genreDto),
        countriesDto = listOf(countryDto, countryDto, countryDto)
    )

    val filmPartial = FilmPartial(
        id = ID,
        name = "Name",
        posterPreviewUrl = "url",
        year = 2024,
        genre = genre,
    )

    val listFilmPartial = listOf(filmPartial, filmPartial, filmPartial)
}