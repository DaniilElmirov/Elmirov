package com.elmirov.tinkofftesttask.navigation.screen

import com.elmirov.tinkofftesttask.ui.favorite.FavoritesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getFavoritesScreen(): FragmentScreen = FragmentScreen {
    FavoritesFragment.newInstance()
}