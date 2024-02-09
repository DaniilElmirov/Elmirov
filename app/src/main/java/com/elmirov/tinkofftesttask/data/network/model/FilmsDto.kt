package com.elmirov.tinkofftesttask.data.network.model

import com.google.gson.annotations.SerializedName

data class FilmsDto(
    @SerializedName("items") val films: List<FilmDto>
)
