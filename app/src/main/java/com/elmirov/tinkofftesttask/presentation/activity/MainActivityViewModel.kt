package com.elmirov.tinkofftesttask.presentation.activity

import androidx.lifecycle.ViewModel
import com.elmirov.tinkofftesttask.navigation.router.MainActivityRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: MainActivityRouter,
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState(currentScreen = Screen.NO_SET))
    val state = _state.asStateFlow()

    fun openPopular() {
        router.openPopular()
        _state.value = _state.value.copy(currentScreen = Screen.POPULAR)
    }

    fun openFavorites() {
        router.openFavorites()
        _state.value = _state.value.copy(currentScreen = Screen.FAVORITES)
    }

    fun infoOpened() {
        _state.value = _state.value.copy(currentScreen = Screen.INFO)
    }

    fun backToPopular() {
        _state.value = _state.value.copy(currentScreen = Screen.POPULAR)
    }
}