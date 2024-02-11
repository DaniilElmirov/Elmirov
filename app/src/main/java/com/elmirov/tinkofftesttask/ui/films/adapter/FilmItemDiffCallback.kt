package com.elmirov.tinkofftesttask.ui.films.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elmirov.tinkofftesttask.domain.entity.FilmFull

class FilmItemDiffCallback : DiffUtil.ItemCallback<FilmFull>() {
    override fun areItemsTheSame(oldItem: FilmFull, newItem: FilmFull): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FilmFull, newItem: FilmFull): Boolean =
        oldItem == newItem
}