package com.elmirov.tinkofftesttask.presentation.activity

import androidx.lifecycle.ViewModel
import com.elmirov.tinkofftesttask.navigation.router.MainActivityRouter
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: MainActivityRouter,
): ViewModel() {

    fun navigate() {
        router.openFilms()
    }
}