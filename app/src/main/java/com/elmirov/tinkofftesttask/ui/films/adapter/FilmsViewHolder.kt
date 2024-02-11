package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.databinding.FilmItemBinding
import com.elmirov.tinkofftesttask.domain.entity.FilmFull

class FilmsViewHolder(
    parent: ViewGroup,
    private val onClick: (FilmFull) -> Unit,
    private val onLongClickListener: (FilmFull) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(filmFull: FilmFull?) {
        filmFull?.let {
            val genre = filmFull.genres.first()
            val year = filmFull.year

            binding.apply {
//                posterPreview.load(filmFull.posterPreviewUrl) {
//                    transformations(RoundedCornersTransformation(12f))
//                } //TODO вернуть нормальное отображение
                name.text = filmFull.name
                genreWithYear.text = String.format(
                    itemView.context.getString(R.string.genre_with_year),
                    genre,
                    year
                )
            }

            itemView.setOnClickListener {
                onClick(filmFull)
            }

            itemView.setOnLongClickListener {
                onLongClickListener(filmFull)
                true
            }
        }
    }
}