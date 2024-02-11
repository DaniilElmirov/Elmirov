package com.elmirov.tinkofftesttask.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmirov.tinkofftesttask.data.local.model.FilmDbModel

@Database(entities = [FilmDbModel::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "favourite_db"
    }

    abstract fun favoriteFilmsDao(): FavoriteFilmsDao
}