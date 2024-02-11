package com.elmirov.tinkofftesttask.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.databinding.FilmItemBinding
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial
import java.util.Locale

class FavoritesViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(filmPartial: FilmPartial) {

        val genre = filmPartial.genre.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else
                it.toString()
        }

        val year = filmPartial.year

        binding.apply {
            favourite.isVisible = true
            posterPreview.load(filmPartial.posterPreviewUrl) {
                transformations(RoundedCornersTransformation(12f))
            }
            name.text = filmPartial.name
            genreWithYear.text = String.format(
                itemView.context.getString(R.string.genre_with_year),
                genre,
                year
            )
        }
    }
}