package com.elmirov.tinkofftesttask.ui.info

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFilmInfoBinding
import com.elmirov.tinkofftesttask.domain.entity.Film
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import com.elmirov.tinkofftesttask.presentation.info.FilmInfoState
import com.elmirov.tinkofftesttask.presentation.info.FilmInfoViewModel
import com.elmirov.tinkofftesttask.util.collectLifecycleFlow
import javax.inject.Inject

class FilmInfoFragment : Fragment() {

    companion object {
        private const val POSTER_SIZE_PERCENT = 0.68

        private const val FILM_ID = "film_id"

        fun newInstance(id: Int): FilmInfoFragment = FilmInfoFragment()
            .apply {
                arguments = Bundle().apply {
                    putInt(FILM_ID, id)
                }
            }
    }

    private var _binding: FragmentFilmInfoBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FilmInfoViewModel::class.java]
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
        _binding = FragmentFilmInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        loadFilmInfo()
        applyState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setOnClickListeners() {
        binding.repeat.setOnClickListener {
            loadFilmInfo()
        }
    }

    private fun loadFilmInfo() {
        val filmId = requireArguments().getInt(FILM_ID)
        viewModel.getFilmInfo(filmId)
    }

    private fun applyState() {
        collectLifecycleFlow(viewModel.state) { state ->
            when (state) {
                FilmInfoState.Initial -> Unit
                FilmInfoState.Loading -> showLoading()
                is FilmInfoState.Content -> showContent(state.content)
                is FilmInfoState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding.film.isVisible = false
        binding.error.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showContent(content: Film) {
        setPosterRightSize()
        binding.progressBar.isVisible = false
        binding.error.isVisible = false

        binding.film.isVisible = true
        binding.poster.load(content.posterUrl)
        binding.name.text = content.name
        binding.description.text = content.description
        binding.genres.text =
            String.format(getString(R.string.genres), content.genres.joinToString(", "))
        binding.countries.text =
            String.format(getString(R.string.countries), content.countries.joinToString(", "))
    }

    private fun showError() {
        binding.progressBar.isVisible = false
        binding.error.isVisible = true
        binding.film.isVisible = false
    }

    private fun setPosterRightSize() {
        val currentScreenSize = Resources.getSystem().displayMetrics.heightPixels
        val guidelineBegin = (currentScreenSize * POSTER_SIZE_PERCENT).toInt()
        binding.guideline.setGuidelineBegin(guidelineBegin)
    }
}