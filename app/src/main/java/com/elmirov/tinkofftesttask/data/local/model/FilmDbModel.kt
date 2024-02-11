package com.elmirov.tinkofftesttask.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FilmDbModel.TABLE_NAME)
data class FilmDbModel(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPreviewUrl: String,
    val year: Int,
    val genre: String,
) {
    companion object {
        const val TABLE_NAME = "favorite_films"
    }
}
