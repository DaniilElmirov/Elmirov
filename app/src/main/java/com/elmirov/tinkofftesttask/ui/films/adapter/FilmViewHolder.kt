package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.databinding.FilmItemBinding
import com.elmirov.tinkofftesttask.domain.entity.Film

class FilmViewHolder(
    parent: ViewGroup,
    private val onClick: (Film?) -> Unit,
    private val onLongClickListener: (Film?) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
) {

    private val binding = FilmItemBinding.bind(itemView)

    fun bind(film: Film?) {
        val genre = film?.genres?.first()
        val year = film?.year

        with(binding) {
            posterPreview.load(film?.posterPreviewUrl)
            name.text = film?.name
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