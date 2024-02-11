package com.elmirov.tinkofftesttask.ui.films.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elmirov.tinkofftesttask.databinding.ErrorItemBinding
import com.elmirov.tinkofftesttask.databinding.ProgressItemBinding

class FilmsLoadStateAdapter(
    private val onRepeatClick: () -> Unit,
) : LoadStateAdapter<FilmsLoadStateAdapter.ItemViewHolder>() {

    private companion object {

        private const val ERROR = -1
        private const val PROGRESS = 0
    }

    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        is LoadState.Error -> ERROR
        LoadState.Loading -> PROGRESS
        is LoadState.NotLoading -> error("Not supported")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder =
        when (loadState) {
            is LoadState.Error -> ErrorViewHolder(
                layoutInflater = LayoutInflater.from(parent.context),
                onRepeatClick = onRepeatClick
            )
            LoadState.Loading -> ProgressViewHolder(layoutInflater = LayoutInflater.from(parent.context))
            is LoadState.NotLoading -> error("Not supported")
        }

    class ErrorViewHolder internal constructor(
        private val binding: ErrorItemBinding,
        private val onRepeatClick: () -> Unit,
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {
            binding.repeat.setOnClickListener {
                onRepeatClick()
            }
        }

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false,
                onRepeatClick: () -> Unit,
            ): ErrorViewHolder {
                return ErrorViewHolder(
                    ErrorItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    ),
                    onRepeatClick
                )
            }
        }
    }

    class ProgressViewHolder internal constructor(
        private val binding: ProgressItemBinding,
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {}

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false,
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ProgressItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }
}