package com.elmirov.tinkofftesttask.ui.favorite.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial
import com.elmirov.tinkofftesttask.ui.films.adapter.FilmItemDiffCallback

class FavoritesAdapter : ListAdapter<FilmPartial, FavoritesViewHolder>(FilmItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(parent)

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}