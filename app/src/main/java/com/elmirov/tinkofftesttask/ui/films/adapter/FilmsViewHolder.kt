package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.databinding.FilmItemBinding
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial

class FilmsViewHolder(
    parent: ViewGroup,
    private val onClick: (FilmPartial) -> Unit,
    private val onLongClickListener: (FilmPartial) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(filmPartial: FilmPartial?) {
        filmPartial?.let {
            val genre = filmPartial.genre
            val year = filmPartial.year

            binding.apply {
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

            itemView.setOnClickListener {
                onClick(filmPartial)
            }

            itemView.setOnLongClickListener {
                onLongClickListener(filmPartial)
                true
            }
        }
    }
}