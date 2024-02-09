package com.elmirov.tinkofftesttask.data.network.api

import com.elmirov.tinkofftesttask.data.network.model.FilmDto
import com.elmirov.tinkofftesttask.data.network.model.FilmsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KinopoiskApi {

    @GET("api/v2.2/films/{id}")
    suspend fun getById(
        @Path("id") id: Int,
    ): FilmDto

    @GET("api/v2.2/films/collections?type=TOP_250_MOVIES&page=1")
    suspend fun getFilms(): FilmsDto
}