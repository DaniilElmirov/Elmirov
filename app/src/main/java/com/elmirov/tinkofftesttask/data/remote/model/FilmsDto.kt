package com.elmirov.tinkofftesttask.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilmsDto(
    @SerializedName("totalPages") val lastPage: Int,
    @SerializedName("items") val films: List<FilmDto>,
)
