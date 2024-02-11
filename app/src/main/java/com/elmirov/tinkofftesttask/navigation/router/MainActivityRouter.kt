package com.elmirov.tinkofftesttask.navigation.router

import com.elmirov.tinkofftesttask.navigation.screen.getFavoritesScreen
import com.elmirov.tinkofftesttask.navigation.screen.getFilmsScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface MainActivityRouter {
    fun openPopular()

    fun openFavorites()
}

class MainActivityRouterImpl @Inject constructor(
    private val router: Router,
) : MainActivityRouter {
    override fun openPopular() {
        router.newRootScreen(getFilmsScreen())
    }

    override fun openFavorites() {
        router.newRootScreen(getFavoritesScreen())
    }
}