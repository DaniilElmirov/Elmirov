package com.elmirov.tinkofftesttask.domain.entity

data class FilmPartial(
    val id: Int,
    val name: String,
    val posterPreviewUrl: String,
    val year: Int,
    val genre: String,
)
