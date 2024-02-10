package com.elmirov.tinkofftesttask.navigation.screen

import com.elmirov.tinkofftesttask.ui.films.FilmsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getFilmsScreen(): FragmentScreen = FragmentScreen {
    FilmsFragment.newInstance()
}