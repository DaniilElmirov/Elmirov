package com.elmirov.tinkofftesttask.ui.films

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFilmsBinding
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import com.elmirov.tinkofftesttask.presentation.films.FilmsViewModel
import com.elmirov.tinkofftesttask.ui.films.adapter.FilmsAdapter
import com.elmirov.tinkofftesttask.ui.films.adapter.FilmsLoadStateAdapter
import com.elmirov.tinkofftesttask.util.collectLifecycleFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmsFragment : Fragment() {

    companion object {
        fun newInstance(): FilmsFragment = FilmsFragment()
    }

    private var _binding: FragmentFilmsBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FilmsViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as TestTaskApplication).component
    }

    private val filmsAdapter by lazy {
        FilmsAdapter(
            onClick = {
                viewModel.openInfo(it.id)
            },
            onLongClickListener = {
                Log.d("onLongClickListener", it.name)
            },
        )
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
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        collectData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setOnClickListeners() {
        binding.repeat.setOnClickListener {
            viewModel.loadFilms()
        }
    }

    private fun collectData() {
        collectLifecycleFlow(viewModel.films) {
            setupAdapter(it)
        }
    }

    private fun setupAdapter(content: PagingData<Film>) {
        binding.contentContainer.adapter = filmsAdapter.withLoadStateFooter(
            FilmsLoadStateAdapter(
                onRepeatClick = {
                    viewModel.loadFilms()
                }
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            filmsAdapter.submitData(content)
        }

        applyAdapterState()
    }

    private fun applyAdapterState() {
        filmsAdapter.addLoadStateListener { state ->
            binding.apply {
                contentContainer.isVisible =
                    state.refresh != LoadState.Loading && state.refresh !is LoadState.Error
                progressBar.isVisible = state.refresh == LoadState.Loading
                error.isVisible = state.refresh is LoadState.Error
            }
        }
    }
}