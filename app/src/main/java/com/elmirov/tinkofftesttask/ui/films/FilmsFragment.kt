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
import androidx.paging.PagingData
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFilmsBinding
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import com.elmirov.tinkofftesttask.presentation.films.FilmsState
import com.elmirov.tinkofftesttask.presentation.films.FilmsViewModel
import com.elmirov.tinkofftesttask.ui.films.adapter.FilmAdapter
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
        applyState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setOnClickListeners() {
        binding.repeat.setOnClickListener {
            viewModel.getFilms()
        }
    }

    private fun applyState() {
        collectLifecycleFlow(viewModel.state) { state ->
            when (state) {
                FilmsState.Initial -> Unit
                FilmsState.Loading -> showLoading()
                is FilmsState.Content -> showContent(state.content)
                is FilmsState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding.films.isVisible = false
        binding.error.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showContent(content: PagingData<Film>) {
        val filmAdapter = FilmAdapter(
            onClick = {
                viewModel.openInfo(it?.id ?: 301)
            },
            onLongClickListener = {
                Log.d("TAGTAG", "${it?.name} long")
            }
        )
        binding.films.adapter = filmAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            filmAdapter.submitData(content)
        }
        binding.error.isVisible = false
        binding.films.isVisible = true
        binding.progressBar.isVisible = false
    }

    private fun showError() {
        binding.progressBar.isVisible = false
        binding.error.isVisible = true
        binding.films.isVisible = false
    }
}