package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.databinding.FilmItemBinding
import com.elmirov.tinkofftesttask.domain.entity.Film

class FilmsViewHolder(
    parent: ViewGroup,
    private val onClick: (Film) -> Unit,
    private val onLongClickListener: (Film) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(film: Film?) {
        film?.let {
            val genre = film.genres.first()
            val year = film.year

            binding.apply {
                posterPreview.load(film.posterPreviewUrl) {
                    transformations(RoundedCornersTransformation(12f))
                }
                name.text = film.name
                genreWithYear.text = String.format(
                    itemView.context.getString(R.string.genre_with_year),
                    genre,
                    year
                )
            }

            itemView.setOnClickListener {
                onClick(film)
            }

            itemView.setOnLongClickListener {
                onLongClickListener(film)
                true
            }
        }
    }
}