package com.elmirov.tinkofftesttask.domain.repository

import com.elmirov.tinkofftesttask.domain.entity.Film

interface FilmRepository {
	suspend fun getById(id: Int): Film

	suspend fun getList(): List<Film>
}