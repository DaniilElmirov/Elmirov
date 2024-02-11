package com.elmirov.tinkofftesttask.navigation.router

import com.elmirov.tinkofftesttask.navigation.screen.getFilmsScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface FilmInfoRouter {
    fun backToFilms()
}

class FilmInfoRouterImpl @Inject constructor(
    private val router: Router,
) : FilmInfoRouter {

    override fun backToFilms() {
        router.backTo(getFilmsScreen())
    }
}