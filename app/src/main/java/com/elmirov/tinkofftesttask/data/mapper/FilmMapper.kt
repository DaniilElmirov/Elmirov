package com.elmirov.tinkofftesttask.data.mapper

import com.elmirov.tinkofftesttask.data.local.model.FilmDbModel
import com.elmirov.tinkofftesttask.data.remote.model.CountryDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmFullDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmPartialDto
import com.elmirov.tinkofftesttask.data.remote.model.GenreDto
import com.elmirov.tinkofftesttask.domain.entity.FilmFull
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

fun FilmFullDto.toEntity(): FilmFull =
    FilmFull(
        id = id,
        name = name,
        posterUrl = posterUrl,
        year = year,
        description = description,
        genres = genresDto.map { it.toGenre() },
        countries = countriesDto.map { it.toCountry() },
    )

fun FilmPartialDto.toEntity(): FilmPartial =
    FilmPartial(
        id = id,
        name = name,
        posterPreviewUrl = posterPreviewUrl,
        year = year,
        genre = genresDto.first().toGenre()
    )

fun FilmDbModel.toEntity(): FilmPartial =
    FilmPartial(
        id = id,
        name = name,
        posterPreviewUrl = posterPreviewUrl,
        year = year,
        genre = genre
    )

fun FilmPartial.toDbModel(): FilmDbModel =
    FilmDbModel(
        id = id,
        name = name,
        posterPreviewUrl = posterPreviewUrl,
        year = year,
        genre = genre,
    )

private fun GenreDto.toGenre(): String =
    genre

private fun CountryDto.toCountry(): String =
    country
