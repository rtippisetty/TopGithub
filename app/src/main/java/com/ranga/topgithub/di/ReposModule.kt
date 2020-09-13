package com.ranga.topgithub.di

import com.ranga.topgithub.reposmodule.ui.RepoListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReposModule {

    @ContributesAndroidInjector
    internal abstract fun reposListActivity(): RepoListActivity
}