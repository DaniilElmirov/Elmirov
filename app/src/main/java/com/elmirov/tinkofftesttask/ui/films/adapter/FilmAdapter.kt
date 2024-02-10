package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elmirov.tinkofftesttask.domain.entity.Film

class FilmAdapter(
    private val onClick: (Film) -> Unit,
    private val onLongClickListener: (Film) -> Unit,
) : ListAdapter<Film, FilmViewHolder>(FilmItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder =
        FilmViewHolder(parent, onClick, onLongClickListener)

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}