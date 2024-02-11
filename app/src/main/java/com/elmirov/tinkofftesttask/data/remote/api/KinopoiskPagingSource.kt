package com.elmirov.tinkofftesttask.data.remote.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elmirov.tinkofftesttask.data.remote.model.FilmDto
import javax.inject.Inject

class KinopoiskPagingSource @Inject constructor(
    private val api: KinopoiskApi,
) : PagingSource<Int, FilmDto>() {

    private companion object {
        private const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, FilmDto>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDto> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = api.getFilms(page)
            val films = response.films
            val lastPage = response.lastPage

            val prevKey = if (page == INITIAL_PAGE) null else page - 1
            val nextKey = if (page == lastPage) null else page + 1

            LoadResult.Page(films, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}