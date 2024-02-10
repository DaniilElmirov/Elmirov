package com.elmirov.tinkofftesttask.di.component

import android.content.Context
import com.elmirov.tinkofftesttask.MainActivity
import com.elmirov.tinkofftesttask.TestTaskApplication
import com.elmirov.tinkofftesttask.di.annotation.ApplicationScope
import com.elmirov.tinkofftesttask.di.module.DataModule
import com.elmirov.tinkofftesttask.di.module.ViewModelModule
import com.elmirov.tinkofftesttask.ui.films.FilmsFragment
import com.elmirov.tinkofftesttask.ui.info.FilmInfoFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(application: TestTaskApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: FilmsFragment)

    fun inject(fragment: FilmInfoFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}