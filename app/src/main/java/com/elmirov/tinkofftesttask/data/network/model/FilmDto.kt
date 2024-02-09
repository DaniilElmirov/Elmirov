package com.elmirov.tinkofftesttask.data.network.model

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameRu") val name: String,
    val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterPreviewUrl: String,
    val year: Int,
    val description: String?, //TODO() возможно стоит использовать разные сущности для списка и по id
    @SerializedName("genres") val genresDto: List<GenreDto>,
    @SerializedName("countries") val countriesDto: List<CountryDto>,
)
