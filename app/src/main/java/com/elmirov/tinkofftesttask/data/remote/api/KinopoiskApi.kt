package com.elmirov.tinkofftesttask.data.remote.api

import com.elmirov.tinkofftesttask.data.remote.model.FilmFullDto
import com.elmirov.tinkofftesttask.data.remote.model.FilmsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {

    companion object {
        const val MAX_PAGE_SIZE = 20
    }

    @GET("api/v2.2/films/{id}")
    suspend fun getById(
        @Path("id") id: Int,
    ): FilmFullDto

    @GET("api/v2.2/films/collections?type=TOP_250_MOVIES")
    suspend fun getFilms(
        @Query("page=") page: Int,
    ): FilmsDto
}