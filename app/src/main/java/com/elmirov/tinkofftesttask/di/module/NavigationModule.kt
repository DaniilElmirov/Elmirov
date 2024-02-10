package com.elmirov.tinkofftesttask.di.module

import com.elmirov.tinkofftesttask.di.annotation.ApplicationScope
import com.elmirov.tinkofftesttask.navigation.router.FilmsRouter
import com.elmirov.tinkofftesttask.navigation.router.FilmsRouterImpl
import com.elmirov.tinkofftesttask.navigation.router.MainActivityRouter
import com.elmirov.tinkofftesttask.navigation.router.MainActivityRouterImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [BindNavigationModule::class])
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @ApplicationScope
    fun provideRouter(): Router = cicerone.router

    @Provides
    @ApplicationScope
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
}

@Module
interface BindNavigationModule {
    @Binds
    @ApplicationScope
    fun bindMainActivityRouter(impl: MainActivityRouterImpl): MainActivityRouter

    @Binds
    @ApplicationScope
    fun bindFilmsRouter(impl: FilmsRouterImpl): FilmsRouter
}