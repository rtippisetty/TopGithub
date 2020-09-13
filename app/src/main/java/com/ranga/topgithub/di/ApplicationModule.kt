package com.ranga.topgithub.di

import android.content.Context
import androidx.room.Room
import com.ranga.topgithub.network.GitReposService
import com.ranga.topgithub.data.manager.GitReposRepository
import com.ranga.topgithub.data.manager.IRepository
import com.ranga.topgithub.data.local.db.GitReposDatabase
import com.ranga.topgithub.data.local.GitReposLocalDataSource
import com.ranga.topgithub.data.remote.GitReposRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ReposRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ReposLocalDataSource

    @JvmStatic
    @Singleton
    @Provides
    @ReposRemoteDataSource
    fun providesGitReposRemoteDataSource(reposService: GitReposService): GitReposRemoteDataSource {
        return GitReposRemoteDataSource(reposService)
    }

    @JvmStatic
    @Singleton
    @Provides
    @ReposLocalDataSource
    fun provideGitReposLocalDataSource(
        database: GitReposDatabase
    ): GitReposLocalDataSource {
        return GitReposLocalDataSource(database.gitRepoDao())
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context): GitReposDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            GitReposDatabase::class.java,
            "Repos.db"
        ).build()
    }
}


@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: GitReposRepository): IRepository
}