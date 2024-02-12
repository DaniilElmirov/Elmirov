package com.elmirov.tinkofftesttask.presentation.activity

data class MainActivityState(
    val currentScreen: Screen
)

enum class Screen {
    POPULAR,
    FAVORITES,
    INFO,
    NO_SET,
}