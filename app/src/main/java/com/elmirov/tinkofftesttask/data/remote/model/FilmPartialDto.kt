package com.elmirov.tinkofftesttask.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilmPartialDto(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameRu") val name: String,
    @SerializedName("posterUrlPreview") val posterPreviewUrl: String,
    val year: Int,
    @SerializedName("genres") val genresDto: List<GenreDto>,
    @SerializedName("countries") val countriesDto: List<CountryDto>,
)
