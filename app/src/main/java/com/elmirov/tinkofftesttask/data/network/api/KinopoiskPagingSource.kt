package com.elmirov.tinkofftesttask.data.network.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elmirov.tinkofftesttask.data.mapper.toEntity
import com.elmirov.tinkofftesttask.domain.entity.Film
import javax.inject.Inject

class KinopoiskPagingSource @Inject constructor(
    private val api: KinopoiskApi,
) : PagingSource<Int, Film>() {

    private companion object {
        private const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {

        val page = params.key ?: INITIAL_PAGE

        val response = api.getFilms(page)
        val lastPage = response.lastPage

        return if (response.films.isNotEmpty()) {
            val films = response.toEntity()
            val prevKey = if (page == INITIAL_PAGE) null else page - 1
            val nextKey = if (page == lastPage) null else page + 1
            LoadResult.Page(films, prevKey, nextKey)
        } else {
            LoadResult.Error(Throwable())
        }
    }
}