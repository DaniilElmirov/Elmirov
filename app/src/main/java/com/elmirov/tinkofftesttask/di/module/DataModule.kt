package com.elmirov.tinkofftesttask.di.module

import android.content.Context
import androidx.room.Room
import com.elmirov.tinkofftesttask.data.datasource.LocalFilmDataSource
import com.elmirov.tinkofftesttask.data.datasource.LocalFilmDataSourceImpl
import com.elmirov.tinkofftesttask.data.datasource.RemoteFilmDataSource
import com.elmirov.tinkofftesttask.data.datasource.RemoteFilmDataSourceImpl
import com.elmirov.tinkofftesttask.data.local.database.FavoriteFilmsDao
import com.elmirov.tinkofftesttask.data.local.database.FavoritesDatabase
import com.elmirov.tinkofftesttask.data.remote.api.KeyInterceptor
import com.elmirov.tinkofftesttask.data.remote.api.KinopoiskApi
import com.elmirov.tinkofftesttask.data.repository.FilmRepositoryImpl
import com.elmirov.tinkofftesttask.di.annotation.ApplicationScope
import com.elmirov.tinkofftesttask.domain.repository.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module(includes = [BindDataModule::class])
class DataModule {

    private companion object {

        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    }

    @Provides
    @ApplicationScope
    fun provideHttpClient(keyInterceptor: KeyInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(keyInterceptor)
            .build()

    @Provides
    @ApplicationScope
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @ApplicationScope
    fun provideKinopoiskApi(retrofit: Retrofit): KinopoiskApi =
        retrofit.create()

    @ApplicationScope
    @Provides
    fun provideFavoritesDatabase(context: Context): FavoritesDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FavoritesDatabase::class.java,
            FavoritesDatabase.DATABASE_NAME
        ).build()

    @ApplicationScope
    @Provides
    fun provideFavoriteCitiesDao(database: FavoritesDatabase): FavoriteFilmsDao =
        database.favoriteFilmsDao()
}

@Module
interface BindDataModule {
    @Binds
    @ApplicationScope
    fun bindFilmRepository(impl: FilmRepositoryImpl): FilmRepository

    @Binds
    @ApplicationScope
    fun bindRemoteFilmDataSource(impl: RemoteFilmDataSourceImpl): RemoteFilmDataSource

    @Binds
    @ApplicationScope
    fun bindLocalFilmDataSource(impl: LocalFilmDataSourceImpl): LocalFilmDataSource
}