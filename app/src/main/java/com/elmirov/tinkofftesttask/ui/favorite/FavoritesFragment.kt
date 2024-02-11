package com.elmirov.tinkofftesttask.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFavoritesBinding
import com.elmirov.tinkofftesttask.domain.entity.FilmPartial
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import com.elmirov.tinkofftesttask.presentation.favorite.FavoritesState
import com.elmirov.tinkofftesttask.presentation.favorite.FavoritesViewModel
import com.elmirov.tinkofftesttask.ui.favorite.adapter.FavoritesAdapter
import com.elmirov.tinkofftesttask.util.collectLifecycleFlow
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    companion object {
        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as TestTaskApplication).component
    }

    private val favoritesAdapter by lazy {
        FavoritesAdapter()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun applyState() {
        collectLifecycleFlow(viewModel.state) { state ->
            when (state) {
                FavoritesState.Initial -> Unit
                FavoritesState.Loading -> showLoading()
                is FavoritesState.Content -> showContent(state.content)
                is FavoritesState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding.apply {
            contentContainer.isVisible = false
            error.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showContent(content: List<FilmPartial>) {
        setupAdapter(content)

        binding.apply {
            contentContainer.isVisible = true
            error.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun setupAdapter(content: List<FilmPartial>) {
        binding.contentContainer.adapter = favoritesAdapter
        favoritesAdapter.submitList(content)
    }

    private fun showError() {
        binding.apply {
            progressBar.isVisible = false
            error.isVisible = true
            contentContainer.isVisible = false
        }
    }
}