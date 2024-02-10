package com.elmirov.tinkofftesttask.ui.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFilmInfoBinding
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import javax.inject.Inject

class FilmInfoFragment : Fragment() {

    companion object {
        fun newInstance(): FilmInfoFragment = FilmInfoFragment()
    }

    private var _binding: FragmentFilmInfoBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
}