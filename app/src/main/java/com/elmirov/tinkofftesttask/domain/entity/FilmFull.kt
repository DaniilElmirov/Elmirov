package com.elmirov.tinkofftesttask.domain.entity

data class FilmFull(
	val id: Int,
	val name: String,
	val posterUrl: String,
	val year: Int,
	val description: String,
	val genres: List<String>,
	val countries: List<String>,
)
