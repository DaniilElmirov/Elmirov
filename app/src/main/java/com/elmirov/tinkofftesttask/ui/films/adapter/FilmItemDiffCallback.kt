package com.elmirov.tinkofftesttask.ui.films.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

class FilmItemDiffCallback : DiffUtil.ItemCallback<FilmPartial>() {
    override fun areItemsTheSame(oldItem: FilmPartial, newItem: FilmPartial): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FilmPartial, newItem: FilmPartial): Boolean =
        oldItem == newItem
}