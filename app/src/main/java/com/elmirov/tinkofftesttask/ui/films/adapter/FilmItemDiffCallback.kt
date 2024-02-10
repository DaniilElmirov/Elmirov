package com.elmirov.tinkofftesttask.ui.films.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elmirov.tinkofftesttask.domain.entity.Film

class FilmItemDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
        oldItem == newItem
}