package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.elmirov.tinkofftesttask.domain.entity.FilmFull

class FilmsAdapter(
    private val onClick: (FilmFull) -> Unit,
    private val onLongClickListener: (FilmFull) -> Unit,
) : PagingDataAdapter<FilmFull, FilmsViewHolder>(FilmItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder =
        FilmsViewHolder(parent, onClick, onLongClickListener)

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}