package com.elmirov.tinkofftesttask.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    companion object {
        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding!!

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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}