package com.elmirov.tinkofftesttask.navigation.router

import com.elmirov.tinkofftesttask.navigation.screen.getFilmInfoScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface FilmsRouter {

    fun openFilmInfo()
}

class FilmsRouterImpl @Inject constructor(
    private val router: Router,
) : FilmsRouter {
    override fun openFilmInfo() {
        router.navigateTo(getFilmInfoScreen())
    }
}