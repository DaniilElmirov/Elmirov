package com.elmirov.tinkofftesttask.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilmFullDto(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameRu") val name: String,
    val posterUrl: String,
    val year: Int,
    val description: String,
    @SerializedName("genres") val genresDto: List<GenreDto>,
    @SerializedName("countries") val countriesDto: List<CountryDto>,
)
