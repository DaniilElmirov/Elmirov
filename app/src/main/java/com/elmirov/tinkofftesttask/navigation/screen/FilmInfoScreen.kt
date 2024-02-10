package com.elmirov.tinkofftesttask.navigation.screen

import com.elmirov.tinkofftesttask.ui.info.FilmInfoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getFilmInfoScreen(): FragmentScreen = FragmentScreen {
    FilmInfoFragment.newInstance()
}