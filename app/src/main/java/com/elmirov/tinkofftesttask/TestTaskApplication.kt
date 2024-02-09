package com.elmirov.tinkofftesttask

import android.app.Application
import com.elmirov.tinkofftesttask.di.component.DaggerApplicationComponent

class TestTaskApplication : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}