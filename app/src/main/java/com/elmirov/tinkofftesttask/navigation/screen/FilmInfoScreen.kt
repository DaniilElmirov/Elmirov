package com.elmirov.tinkofftesttask.navigation.screen

import com.elmirov.tinkofftesttask.ui.info.FilmInfoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getFilmInfoScreen(id: Int): FragmentScreen = FragmentScreen {
    FilmInfoFragment.newInstance(id)
}