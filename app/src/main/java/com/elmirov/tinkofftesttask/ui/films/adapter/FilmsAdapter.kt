package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

class FilmsAdapter(
    private val onClick: (FilmPartial) -> Unit,
    private val onLongClickListener: (FilmPartial) -> Unit,
) : PagingDataAdapter<FilmPartial, FilmsViewHolder>(FilmItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder =
        FilmsViewHolder(parent, onClick, onLongClickListener)

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}