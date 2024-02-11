package com.elmirov.tinkofftesttask.data.mapper

import com.elmirov.tinkofftesttask.data.remote.model.CountryDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmsDto
import com.elmirov.tinkofftesttask.data.remote.model.GenreDto
import com.elmirov.tinkofftesttask.domain.entity.Film

fun FilmsDto.toEntity(): List<Film> =
    films.map {
        it.toEntity()
    }

fun FilmDto.toEntity(): Film =
    Film(
        id = id,
        name = name,
        posterUrl = posterUrl,
        posterPreviewUrl = posterPreviewUrl,
        year = year,
        description = description ?: "",
        genres = genresDto.map { it.toGenre() },
        countries = countriesDto.map { it.toCountry() },
    )

private fun GenreDto.toGenre(): String =
    genre

private fun CountryDto.toCountry(): String =
    country
