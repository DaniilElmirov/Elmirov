package com.elmirov.tinkofftesttask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elmirov.tinkofftesttask.R
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.databinding.ActivityMainBinding
import com.elmirov.tinkofftesttask.presentation.ViewModelFactory
import com.elmirov.tinkofftesttask.presentation.activity.MainActivityViewModel
import com.elmirov.tinkofftesttask.presentation.activity.Screen
import com.elmirov.tinkofftesttask.ui.films.FilmsFragment
import com.elmirov.tinkofftesttask.ui.info.FilmInfoFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TestTaskApplication).component.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            viewModel.openPopular()
        }

        catchInfoScreen()
        setOnClickListeners()
        applyState()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun catchInfoScreen() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currentScreen = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (currentScreen is FilmInfoFragment)
                viewModel.infoOpened()

            if (currentScreen is FilmsFragment)
                viewModel.backToPopular()
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            favorite.setOnClickListener {
                viewModel.openFavorites()
            }

            popular.setOnClickListener {
                viewModel.openPopular()
            }
        }
    }

    private fun applyState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it.currentScreen) {
                    Screen.POPULAR -> applyPopular()
                    Screen.FAVORITES -> applyFavorites()
                    Screen.INFO -> applyInfo()
                    Screen.NO_SET -> Unit
                }
            }
        }
    }

    private fun applyPopular() {
        binding.apply {
            popular.isEnabled = false
            popular.isVisible = true

            favorite.isEnabled = true
            favorite.isVisible = true
        }
    }

    private fun applyFavorites() {
        binding.apply {
            popular.isEnabled = true
            popular.isVisible = true

            favorite.isEnabled = false
            favorite.isVisible = true
        }
    }

    private fun applyInfo() {
        binding.apply {
            popular.isEnabled = false
            popular.isVisible = false

            favorite.isEnabled = false
            favorite.isVisible = false
        }
    }
}