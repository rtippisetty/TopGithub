package com.ranga.topgithub.di

import android.content.Context
import com.ranga.topgithub.TopGitReposApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ReposModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent: AndroidInjector<TopGitReposApplication> {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}