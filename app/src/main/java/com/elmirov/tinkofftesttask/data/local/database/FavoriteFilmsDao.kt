package com.elmirov.tinkofftesttask.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elmirov.tinkofftesttask.data.local.model.FilmDbModel

@Dao
interface FavoriteFilmsDao {

    @Query("SELECT * FROM ${FilmDbModel.TABLE_NAME}")
    suspend fun getAll(): List<FilmDbModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(film: FilmDbModel)
}